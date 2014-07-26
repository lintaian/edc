define(['angular', 'jquery', 'moment'], function(angular, $, moment) {
	return angular.module('datetimepicker', []).constant('dateTimePickerConfig', {
	    startView: 'day',
	    minView: 'second',
	    minuteStep: 1,
	    secondStep: 1,
	    dropdownSelector: null,
	    autoHide: false,
	    liveUpdate: true
	}).constant('dateTimePickerConfigValidation', function (configuration) {
	    "use strict";
	    var validOptions = ['startView', 'minView', 'minuteStep', 'secondStep', 'dropdownSelector',
	                        'showPicker', 'next', 'timeType', 'autoHide', 'liveUpdate'];
	    for (var prop in configuration) {
	        if (configuration.hasOwnProperty(prop)) {
	            if (validOptions.indexOf(prop) < 0) {
	                throw ("invalid option: " + prop);
	            }
	        }
	    }
	    // Order of the elements in the validViews array is significant.
	    var validViews = ['second', 'minute', 'hour', 'day', 'month', 'year'];
	    if (validViews.indexOf(configuration.startView) < 0) {
	        throw ("invalid startView value: " + configuration.startView);
	    }
	    if (validViews.indexOf(configuration.minView) < 0) {
	        throw ("invalid minView value: " + configuration.minView);
	    }
	    if (validViews.indexOf(configuration.minView) > validViews.indexOf(configuration.startView)) {
	        throw ("startView must be greater than minView");
	    }
	    if (!angular.isNumber(configuration.minuteStep)) {
	        throw ("minuteStep must be numeric");
	    }
	    if (configuration.minuteStep <= 0 || configuration.minuteStep >= 60) {
	        throw ("minuteStep must be greater than zero and less than 60");
	    }
	    if (configuration.dropdownSelector !== null && !angular.isString(configuration.dropdownSelector)) {
	        throw ("dropdownSelector must be a string");
	    }
	  }
	).directive('datetimePicker', ['dateTimePickerConfig', 'dateTimePickerConfigValidation', function (defaultConfig, validateConfigurationFunction) {
	    "use strict";

	    return {
	        restrict: 'AE',
	        require: 'ngModel',
	        template: "<div class='datetimepicker'>" +
	            "<table class='table-condensed'>" +
	            "   <thead>" +
	            "       <tr>" +
	            "           <th class='left'" +
	            "               data-ng-click=\"changeView(data.currentView, data.leftDate, $event)\"" +
	            "               ><i class='icon-arrow-left'/>&lt;</th>" +
	            "           <th class='switch' colspan='5'" +
	            "               data-ng-click=\"changeView(data.previousView, data.currentDate, $event)\"" +
	            ">{{ data.title }}</th>" +
	            "           <th class='right'" +
	            "               data-ng-click=\"changeView(data.currentView, data.rightDate, $event)\"" +
	            "             >&gt;<i class='icon-arrow-right'/></th>" +
	            "       </tr>" +
	            "       <tr>" +
	            "           <th class='dow' data-ng-repeat='day in data.dayNames' >{{ day }}</th>" +
	            "       </tr>" +
	            "   </thead>" +
	            "   <tbody>" +
	            '       <tr data-ng-class=\'{ hide: data.currentView == "day" }\' >' +
	            "           <td colspan='7' >" +
	            "              <span class='{{ data.currentView }}' " +
	            "                  data-ng-repeat='dateValue in data.dates'  " +
	            "                  data-ng-class='{active: dateValue.active, past: dateValue.past, future: dateValue.future}' " +
	            "                  data-ng-click=\"changeView(data.nextView, dateValue.date, $event)\">{{ dateValue.display }}</span> " +
	            "           </td>" +
	            "       </tr>" +
	            '       <tr data-ng-show=\'{{ data.currentView == "day" }}\' data-ng-repeat=\'week in data.weeks\'>' +
	            "           <td data-ng-repeat='dateValue in week.dates' " +
	            "               data-ng-click=\"changeView(data.nextView, dateValue.date, $event)\"" +
	            "               class='day' " +
	            "               data-ng-class='{active: dateValue.active, past: dateValue.past, future: dateValue.future}'>{{ dateValue.display }}</td>" +
	            "       </tr>" +
	            "   </tbody>" +
	            "</table></div>",
	        scope: {
	            ngModel: "=ngModel"
	        },
	        replace: true,
	        link: function (scope, element, attrs) {
	            if(!scope.data){
	                scope.data = {};
	            }
	            var directiveConfig = {};
	            if (attrs.datetimepickerConfig) {
	                directiveConfig = scope.$eval(attrs.datetimepickerConfig);
	            }
	            var configuration = {};
	            angular.extend(configuration, defaultConfig, directiveConfig);
	            validateConfigurationFunction(configuration);
	            var validViews = ['second', 'minute', 'hour', 'day', 'month', 'year'];
	            var dataFactory = {
	                year: function (unixDate) {
	                    var selectedDate = moment(unixDate).startOf('year');
	                    // View starts one year before the decade starts and ends one year after the decade ends
	                    // i.e. passing in a date of 1/1/2013 will give a range of 2009 to 2020
	                    // Truncate the last digit from the current year and subtract 1 to get the start of the decade
	                    var startDecade = (parseInt(selectedDate.year() / 10, 10) * 10);
	                    var startDate = moment(selectedDate).year(startDecade - 1).startOf('year');
	                    var activeDate = scope.ngModel ? moment(scope.ngModel.time).year() : 0;
	                    var result = {
	                        'currentView': 'year',
	                        'nextView': configuration.minView === 'year' ? 'setTime' : configuration.next ? configuration.next : 'month',
	                        'title': activeDate,
	                        'currentDate': selectedDate.valueOf(),	                        
	                        'leftDate': moment(startDate).subtract(8, 'year').valueOf(),
	                        'rightDate': moment(startDate).add(12, 'year').valueOf(),
	                        'dates': []
	                    };
	                    for (var i = 0; i < 12; i++) {
	                        var yearMoment = moment(startDate).add(i, 'years');
	                        var dateValue = {
	                        'date': yearMoment.valueOf(),
	                        'display': yearMoment.format('YYYY'),
	                        'past': yearMoment.year() < startDecade,
	                        'future': yearMoment.year() > startDecade + 9,
	                        'active': yearMoment.year() === activeDate
	                        };
	                        result.dates.push(dateValue);
	                    }
	                    return result;
	                },
	                semester: function (unixDate) {
	                    var startDate = moment.utc(unixDate).startOf('year');
	                    var activeDate = scope.ngModel ? moment(scope.ngModel).format('YYYY-MM') : 0;
	                    var result = {
	                        'previousView': 'year',
	                        'currentView': 'month',
	                        'nextView': 'setTime',
	                        'currentDate': startDate.valueOf(),
	                        'title': startDate.format('YYYY'),
	                        'leftDate': moment.utc(startDate).subtract(1, 'year').valueOf(),
	                        'rightDate': moment.utc(startDate).add(1, 'year').valueOf(),
	                        'dates': []
	                    };
	                    for (var i = 0; i < 12; i++) {
	                        if(i%6 == 0){
	                            var monthMoment = moment.utc(startDate).add(i, 'months');
	                            if(configuration.timeType == 'end'){
	                                monthMoment = moment.utc(startDate).add(i + 5, 'months');
	                            }
	                            var dateValue = {
	                                'date': monthMoment.valueOf(),
	                                'display': monthMoment.format('MM'),
	                                'active': monthMoment.format('YYYY-MM') === activeDate
	                            };
	                            result.dates.push(dateValue);
	                        }
	                    }
	                    return result;
	                },
	                quarter: function (unixDate) {
	                    var startDate = moment.utc(unixDate).startOf('year');
	                    var activeDate = scope.ngModel ? moment(scope.ngModel).format('YYYY-MM') : 0;
	                    var result = {
	                        'previousView': 'year',
	                        'currentView': 'month',
	                        'nextView': 'setTime',
	                        'currentDate': startDate.valueOf(),
	                        'title': startDate.format('YYYY'),
	                        'leftDate': moment.utc(startDate).subtract(1, 'year').valueOf(),
	                        'rightDate': moment.utc(startDate).add(1, 'year').valueOf(),
	                        'dates': []
	                    };
	                    for (var i = 0; i < 12; i++) {
	                        if(i%3 == 0){
	                            var monthMoment = moment.utc(startDate).add(i, 'months');
	                            if(configuration.timeType == 'end'){
	                                monthMoment = moment.utc(startDate).add(i + 2, 'months');
	                            }
	                            var dateValue = {
	                                'date': monthMoment.valueOf(),
	                                'display': monthMoment.format('MM'),
	                                'active': monthMoment.format('YYYY-MM') === activeDate
	                            };
	                            result.dates.push(dateValue);
	                        }
	                    }
	                    return result;
	                },
	                month: function (unixDate) {
	                	var selectedDate = moment(unixDate).startOf('month'); 
	                    var startDate = moment(unixDate).startOf('year');
	                    var activeDate = scope.ngModel ? moment(scope.ngModel.time).format('YYYY-MM') : '';
	                    var result = {
	                        'previousView': 'year',
	                        'currentView': 'month',
	                        'nextView': configuration.minView === 'month' ? 'setTime' : 'day',
	                        'currentDate': selectedDate.valueOf(),
	                        'title': selectedDate.format('YYYY-MM'),
	                        'leftDate': moment(selectedDate).subtract(1, 'year').valueOf(),
	                        'rightDate': moment(selectedDate).add(1, 'year').valueOf(),
	                        'dates': []
	                    };
	                    for (var i = 0; i < 12; i++) {
	                        var monthMoment = moment(startDate).add(i, 'months');
	                        var dateValue = {
	                            'date': monthMoment.valueOf(),
	                            'display': monthMoment.format('MM'),
	                            'active': monthMoment.format('YYYY-MM') === activeDate
	                        };
	                        result.dates.push(dateValue);
	                    }
	                    return result;
	                },
	                day: function (unixDate) {
	                    var selectedDate = moment(unixDate).startOf('day');
	                    var startOfMonth = moment(selectedDate).startOf('month');
	                    var endOfMonth = moment(selectedDate).endOf('month');
	                    // ToDo: Update to account for starting on days other than Sunday.
	                    var startDate = moment(startOfMonth).subtract(startOfMonth.day(), 'days');
	                    var activeDate = scope.ngModel ? moment(scope.ngModel.time).format('YYYY-MM-DD') : '';
	                    var result = {
	                        'previousView': 'month',
	                        'currentView': 'day',
	                        'nextView': configuration.minView === 'day' ? 'setTime' : 'hour',
	                        'currentDate': selectedDate.valueOf(),
	                        'title': selectedDate.format('YYYY-MM-DD'),
	                        'leftDate': moment(selectedDate).subtract(1, 'months').valueOf(),
	                        'rightDate': moment(selectedDate).add(1, 'months').valueOf(),
	                        'dayNames': ['日','一','二','三','四','五','六'],
	                         'weeks': []
	                    };
	                    /*for (var dayNumber = 0; dayNumber < 7; dayNumber++) {
	                      result.dayNames.push(moment().day(dayNumber).format('dd'));
	                    }*/
	                    for (var i = 0; i < 6; i++) {
	                        var week = { dates: [] };
	                        for (var j = 0; j < 7; j++) {
	                            var monthMoment = moment(startDate).add((i * 7) + j, 'days');
	                            var dateValue = {
	                                'date': monthMoment.valueOf(),
	                                'display': monthMoment.format('D'),
	                                'active': monthMoment.format('YYYY-MM-DD') === activeDate,
	                                'past': monthMoment.isBefore(startOfMonth),
	                                'future': monthMoment.isAfter(endOfMonth)
	                            };
	                            week.dates.push(dateValue);
	                        }
	                        result.weeks.push(week);
	                    }
	                    return result;
	                },
	                hour: function (unixDate) {
	                    var selectedDate = moment(unixDate).startOf('hour');
	                    var startDate = moment(unixDate).startOf('day');
	                    var activeFormat = scope.ngModel ? moment(scope.ngModel.format).format('YYYY-MM-DD HH') : '';
	                    var result = {
	                        'previousView': 'day',
	                        'currentView': 'hour',
	                        'nextView': configuration.minView === 'hour' ? 'setTime' : 'minute',
	                        'currentDate': selectedDate.valueOf(),
	                        'title': selectedDate.format('YYYY-MM-DD HH'),
	                        'leftDate': moment(selectedDate).subtract(1, 'days').valueOf(),
	                        'rightDate': moment(selectedDate).add(1, 'days').valueOf(),
	                        'dates': []
	                    };
	                    for (var i = 0; i < 24; i++) {
	                        var hourMoment = moment(startDate).add(i, 'hours');
	                        var dateValue = {
	                            'date': hourMoment.valueOf(),
	                            'display': hourMoment.format('H'),
	                            'active': hourMoment.format('YYYY-MM-DD HH') === activeFormat
	                        };
	                        result.dates.push(dateValue);
	                    }
	                    return result;
	                },
	                minute: function (unixDate) {
	                    var selectedDate = moment(unixDate).startOf('minute');
	                    var startDate = moment(unixDate).startOf('hour');
	                    var activeFormat = scope.ngModel ? moment(scope.ngModel.time).format('YYYY-MM-DD HH:mm') : '';
	                    var result = {
	                        'previousView': 'hour',
	                        'currentView': 'minute',
	                        'nextView': configuration.minView === 'minute' ? 'setTime' : 'second',
	                        'currentDate': selectedDate.valueOf(),
	                        'title': selectedDate.format('YYYY-MM-DD HH:mm'),
	                        'leftDate': moment(selectedDate).subtract(1, 'hours').valueOf(),
	                        'rightDate': moment(selectedDate).add(1, 'hours').valueOf(),
	                        'dates': []
	                    };
	                    var limit = 60 / configuration.minuteStep;
	                    for (var i = 0; i < limit; i++) {
	                        var hourMoment = moment(startDate).add(i * configuration.minuteStep, 'minute');
	                        var dateValue = {
	                            'date': hourMoment.valueOf(),
	                            'display': hourMoment.format('mm'),
	                            'active': hourMoment.format('YYYY-MM-DD HH:mm') === activeFormat
	                        };
	                        result.dates.push(dateValue);
	                    }
	                    return result;
	                },
	                second: function(unixDate) {
	                	var selectedDate = moment(unixDate).startOf('second');
	                	var startDate = moment(unixDate).startOf('minute');
	                    var activeFormat = scope.ngModel ? moment(scope.ngModel.time).format('YYYY-MM-DD HH:mm:ss') : '';
	                    var result = {
	                        'previousView': 'minute',
	                        'currentView': 'second',
	                        'nextView': 'setTime',
	                        'currentDate': selectedDate.valueOf(),
	                        'title': selectedDate.format('YYYY-MM-DD HH:mm:ss'),
	                        'leftDate': moment(selectedDate).subtract(1, 'minutes').valueOf(),
	                        'rightDate': moment(selectedDate).add(1, 'minutes').valueOf(),
	                        'dates': []
	                    };
	                    var limit = 60 / configuration.secondStep;
	                    for (var i = 0; i < limit; i++) {
	                        var hourMoment = moment(startDate).add(i * configuration.secondStep, 'second');
	                        var dateValue = {
	                            'date': hourMoment.valueOf(),
	                            'display': hourMoment.format('ss'),
	                            'active': hourMoment.format('YYYY-MM-DD HH:mm:ss') === activeFormat
	                        };
	                        result.dates.push(dateValue);
	                    }
	                    return result;
	                },
	                setTime: function (unixDate) {
	                	var date = moment(unixDate),
	                    	view = scope.data.currentView,
	                    	time = 0,
	                    	isEnd = configuration.timeType == 'end';
	                    switch (view) {
						case 'year':
							time = isEnd ? date.endOf('year') : date.startOf('year');
							break;
						case 'month':
							time = isEnd ? date.endOf('month') : date.startOf('month');
							break;
						case 'day':
							time = isEnd ? date.endOf('day') : date.startOf('day');
							break;
						case 'hour':
							time = isEnd ? date.endOf('hour') : date.startOf('hour');
							break;
						case 'minute':
							time = isEnd ? date.endOf('minute') : date.startOf('minute');
							break;
						case 'second':
							time = isEnd ? date.endOf('second') : date.startOf('second');
							break;
						default:
							break;
						}
	                    scope.ngModel = {
	                        date: date,
	                        time: time.valueOf(),
	                        format: formatTime(unixDate,view)
	                    };
	                }
	            };
	            scope.changeView = function (viewName, unixDate, event, nonSetTime) {
	                if(viewName == 'month' && configuration.next){
	                    viewName = configuration.next;
	                }
	                if (event) {
	                    event.stopPropagation();
	                    event.preventDefault();
	                }
                	if (viewName && (unixDate > -Infinity) && dataFactory[viewName]) {
                		if(viewName == 'setTime') {
                			dataFactory['setTime'](unixDate);
                			if(configuration.autoHide) {
                				scope.ngModel.hide = true;
                			}
                		} else {
                			scope.data = dataFactory[viewName](unixDate);
                			if(!nonSetTime && configuration.liveUpdate) {
                				dataFactory['setTime'](unixDate);
                			}
                		}
                	}
	            };
	            scope.changeView(configuration.startView, moment());
	            scope.$watch('ngModel', function () {
	                var view = !scope.data.currentView ? configuration.startView : scope.data.currentView;
	                var time = scope.ngModel ? scope.ngModel.time : moment();
	                scope.changeView(view, time, null, true);
	            });
	        }
	    };
	}]);
});
function formatTime(date,level){
    format = '';
    switch(level){
        case 'year':format = 'YYYY';break;
        case 'month':format = 'YYYY-MM';break;
        case 'day':format = 'YYYY-MM-DD';break;
        case 'hour':format = 'YYYY-MM-DD HH';break;
        case 'minute':format = 'YYYY-MM-DD HH:mm';break;
        case 'second':format = 'YYYY-MM-DD HH:mm:ss';break;
    }
    return moment(date).format(format);
}
