function Index($routeParams) {
	var type = $routeParams.type;
	window.location.href = 'main#/' + type;
}