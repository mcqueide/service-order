

angular.module('serviceorderangular').controller('SearchClientController', function($scope, $http, $filter, ClientResource ) {
	$scope.clients = ClientResource.queryAll();
	
	console.log($scope.clients);
});