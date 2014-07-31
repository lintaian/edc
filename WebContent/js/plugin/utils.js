/**
 * 冻结table
 * @param TableID 要锁定的Table的ID
 * @param FixColumnNumber 要锁定列的个数
 * @constructor
 */
function FixTable(TableID, FixColumnNumber) {
    var width = 0,height = 0;
    var oldTable = $("#" + TableID),
        tableLayout = $("#" + TableID + "_tableLayout");
    height = oldTable.height()+17;

    if (tableLayout.length != 0) {
        tableLayout.css({height:height});
        width = tableLayout.width();
        height = tableLayout.height();
        tableLayout.before($("#" + TableID));
        tableLayout.empty();
    } else {
        $("#" + TableID).after("<div id='" + TableID + "_tableLayout' style='overflow:hidden;max-height:500px;height:"+height+"px; width:100%;'></div>");
        tableLayout = $("#" + TableID + "_tableLayout");
        width = tableLayout.width();
        height = tableLayout.height();
    }
    $('<div id="' + TableID + '_tableFix"></div>'
        + '<div id="' + TableID + '_tableHead"></div>'
        + '<div id="' + TableID + '_tableColumn"></div>'
        + '<div id="' + TableID + '_tableData"></div>').appendTo(tableLayout);
    oldTable.width(width-17);
    var tableFix = $("#" + TableID + "_tableFix"),
        tableHead = $("#" + TableID + "_tableHead"),
        tableColumn = $("#" + TableID + "_tableColumn"),
        tableData = $("#" + TableID + "_tableData");
    var tableFixClone = oldTable.clone(true);
    tableFixClone.attr("id", TableID + "_tableFixClone");
    tableFix.append(tableFixClone);
    var tableHeadClone = oldTable.clone(true);
    tableHeadClone.attr("id", TableID + "_tableHeadClone");
    tableHead.append(tableHeadClone);
    var tableColumnClone = oldTable.clone(true);
    tableColumnClone.attr("id", TableID + "_tableColumnClone");
    tableColumn.append(tableColumnClone);
    tableData.append(oldTable);
    $("#" + TableID + "_tableLayout table").each(function () {
        $(this).css("margin", "0");
    });
    var HeadHeight = $("#" + TableID + "_tableHead thead").height();
    HeadHeight += 2;
    tableHead.css("height", HeadHeight);
    tableFix.css("height", HeadHeight);
    var ColumnsWidth = 0;
    var ColumnsNumber = 0;
    $("#" + TableID + "_tableColumn tr:last td:lt(" + FixColumnNumber + ")").each(function () {
        ColumnsWidth += $(this).outerWidth(true);
        ColumnsNumber++;
    });
    ColumnsWidth += 2;
    if ($.browser.msie) {
        switch ($.browser.version) {
            case "7.0":
                if (ColumnsNumber >= 3) ColumnsWidth--;
                break;
            case "8.0":
                if (ColumnsNumber >= 2) ColumnsWidth--;
                break;
        }
    }
    tableColumn.css("width", ColumnsWidth);
    tableFix.css("width", ColumnsWidth);
    tableData.scroll(function () {
        tableHead.scrollLeft(tableData.scrollLeft());
        tableColumn.scrollTop(tableData.scrollTop());
    });
    tableFix.css({ "overflow": "hidden", "position": "relative", "z-index": "50", "background-color": "Silver" });
    tableHead.css({ "overflow": "hidden", "width": width - 17, "position": "relative", "z-index": "45", "background-color": "Silver" });
    tableColumn.css({ "overflow": "hidden", "height": height - 17, "position": "relative", "z-index": "40", "background-color": "Silver" });
    tableData.css({ "overflow": "auto",overflowX:'auto',overflowY:'auto', "width": width, "height": height, "position": "relative", "z-index": "35" });
    var tableFixTable = $("#" + TableID + "_tableFix table");
    if (tableHead.width() > tableFixTable.width()) {
        tableHead.css("width", tableFixTable.width());
        tableData.css("width", tableFixTable.width() + 17);
    }
    var tableColumnTable = $("#" + TableID + "_tableColumn table");
    if (tableColumn.height() > tableColumnTable.height()) {
        tableColumn.css("height", tableColumnTable.height());
        tableData.css("height", tableColumnTable.height() + 17);
    }
    tableFix.offset(tableLayout.offset());
    tableHead.offset(tableLayout.offset());
    tableColumn.offset(tableLayout.offset());
    tableData.offset(tableLayout.offset());
}

function numberic(num){
    return num ? parseFloat((num + '').replace(/,/g,'')) : 0;
}
function browserInfo() {
    var ua = navigator.userAgent.toLowerCase(),
        result = {'browser':'','version':0},
        s;
    (s = ua.match(/msie ([\d.]+)/)) ? result.browser = 'IE' :
        (s = ua.match(/firefox\/([\d.]+)/)) ? result.browser = 'Firefox' :
            (s = ua.match(/chrome\/([\d.]+)/)) ? result.browser = 'Chrome' :
                (s = ua.match(/opera.([\d.]+)/)) ? result.browser = 'Opera' :
                    (s = ua.match(/version\/([\d.]+).*safari/)) ? result.browser = 'Safari' : 0;
    result.version = s[1];
    return result;
}
function ltIE8() {
    var browser = browserInfo();
    if(browser.browser == 'IE' && parseFloat(browser.version) < 9.0)
        return true;
    return false;
}