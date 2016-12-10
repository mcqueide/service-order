/**
 * Created by mcqueide on 10/12/16.
 */
'use strict';

angular.module('serviceOrder')
    .controller('ClientController',['$scope','clientService',function ($scope,clientService) {
        $scope.test = "test";
    }]);