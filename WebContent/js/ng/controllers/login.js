function Login($scope, User) {
	$scope.user = {
		username: '',
		password: '',
		role: 'admin'
	}
	$scope.login = function() {
		if ('' != $scope.user.username && '' != $scope.user.password) {
			User.login($scope.user, function(data) {
				if (data.status) {
					window.location.href="main";
				} else {
					$scope.setErrorMsg(data.msg);
				}
			}, function(e) {
				$scope.setErrorMsg('服务器错误!');
			});
		} else {
			$scope.setErrorMsg('用户名和密码不能为空!');
		}
	}
	
	$scope.setErrorMsg = function(msg) {
		$scope.errorMsg = msg;
//		$('.errorMsg').children('.alert').removeClass('ng-hide');
//		$('.errorMsg').fadeOut(5000, function() {
//			$(this).children('.alert').addClass('ng-hide');
//			$(this).show();
//		});
	} 
}