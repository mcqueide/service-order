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

        }])
        .controller('NewClientController',['$scope','clientService',function ($scope,clientService) {

            $scope.message = 'Loading ...';
            $scope.client = {id:'',name:'',adress:'',homePhone:'',bisenessPhone:'',ordemServicos:[],phones:[]};
            $scope.phone = {id:'',brand:'',model:'',state:'',esn:''};

            $scope.registerClient = function () {
                clientService.getClients().save($scope.client,
                    function () {
                        $scope.client =
                            {id:'',name:'',adress:'',homePhone:'',bisenessPhone:'',ordemServicos:[],phones:[]};
                        $scope.phone = {id:'',brand:'',model:'',state:'',esn:''};
                        $scope.message = 'Client registered sucessful';
                        console.log('sucess');
                    },function (error) {
                        console.log(error);
                    });
            };

            $scope.addPhone = function () {
                $scope.client.phones.push($scope.phone);

                $scope.phone = {id:'',brand:'',model:'',state:'',esn:'',client:{}};

                console.log($scope.client);
            };

            $scope.removeClientPhone = function (phone) {
                var index = $scope.client.phones.indexOf(phone);
                $scope.client.phones.splice(index,1);
            };
        }])
        .controller('EditClientController',['$scope','$routeParams','clientService',
            function ($scope,$routeParams,clientService) {
                $scope.message = 'Loading ...';
                $scope.client = {};
                $scope.phone = {id:'',brand:'',model:'',state:'',esn:''};
                clientService.getClients().get({id:$routeParams.id})
                    .$promise.then(
                    function (response) {
                        $scope.client = response;
                    },function (error) {
                        $scope.message = 'Error: ' + error;
                    }
                    );

                /*$http.get('http://localhost:8080/service-order-rest/rest/client/' + $routeParams.id)
                    .success(function(client) {
                        $scope.client = client;
                    })
                    .error(function(error) {
                        console.log(error);
                        $scope.mensagem = 'Não foi possível obter a foto'
                    });*/

        }]);
})();