/**
 * Created by mcqueide on 10/12/16.
 */

(function () {
    'use strict';

    angular.module('serviceOrder')
        .controller('ClientController',['$scope','clientService',function ($scope,clientService) {

            $scope.message = 'Loading ...';
            clientService.getClients().query(
                function (response) {
                    $scope.clients = response;
                },
                function (response) {
                    $scope.message = 'Error: ' + response.status + ' ' + response.statusText;
                }
            );
        }]);
})();