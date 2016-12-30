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

                $scope.clientForm.brand.$setPristine();
                $scope.clientForm.model.$setPristine();
                $scope.clientForm.state.$setPristine();
                $scope.clientForm.esn.$setPristine();

            };

            $scope.resetPhone = function () {
                $scope.phone = {id:'',brand:'',model:'',state:'',esn:''};
                $scope.clientForm.brand.$setPristine();
                $scope.clientForm.model.$setPristine();
                $scope.clientForm.state.$setPristine();
                $scope.clientForm.esn.$setPristine();
            };

            $scope.removeClientPhone = function (phone) {
                var index = $scope.client.phones.indexOf(phone);
                $scope.client.phones.splice(index,1);
            };
        }])
        .controller('EditClientController',['$scope','$routeParams','clientService',
            function ($scope,$routeParams,clientService) {
                $scope.showMessage = false;
                $scope.message = '';
                $scope.classMessage = '';
                $scope.client = {};
                $scope.phone = {id:'',brand:'',model:'',state:'',esn:''};
                $scope.phonesToBeRemoved = [];

                clientService.getClients().get({id:$routeParams.id})
                    .$promise.then(
                    function (response) {
                        $scope.client = response;
                    },function (error) {
                        $scope.message = 'Error: ' + error;
                    }
                    );

                $scope.addPhone = function () {
                    $scope.client.phones.push($scope.phone);

                    $scope.phone = {id:'',brand:'',model:'',state:'',esn:'',client:{}};

                    $scope.clientForm.brand.$setPristine();
                    $scope.clientForm.model.$setPristine();
                    $scope.clientForm.state.$setPristine();
                    $scope.clientForm.esn.$setPristine();
                };

                $scope.removeClientPhone = function (phone) {
                    $scope.phonesToBeRemoved.push(phone);

                    var index = $scope.client.phones.indexOf(phone);
                    $scope.client.phones.splice(index,1);
                };

                $scope.resetPhone = function () {
                    $scope.phone = {id:'',brand:'',model:'',state:'',esn:''};
                    $scope.clientForm.brand.$setPristine();
                    $scope.clientForm.model.$setPristine();
                    $scope.clientForm.state.$setPristine();
                    $scope.clientForm.esn.$setPristine();
                };

                $scope.registerClient = function () {
                    $scope.clientPhoneUpdateVO = {client:$scope.client,phonesToBeRemoved:$scope.phonesToBeRemoved};
                    clientService.getClients().update({id:$scope.client.id},$scope.clientPhoneUpdateVO,
                        function () {
                            $scope.showMessage = true;
                            $scope.message = 'Client registered sucessful';
                            $scope.classMessage = 'alert alert-success';
                        },function (error) {
                            $scope.showMessage = true;
                            $scope.message = error;
                            $scope.classMessage = 'alert alert-danger';
                        });
                };

                $scope.closeMessage = function () {
                    $scope.showMessage = false;
                    $scope.message = '';
                    $scope.classMessage = '';
                }
        }]);
})();