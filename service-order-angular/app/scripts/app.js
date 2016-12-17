/**
 * Created by mcqueide on 10/12/16.
 */
(function () {

    'use strict';

    angular.module('serviceOrder',['ngRoute','ngResource'])
        .config(function ($routeProvider,$locationProvider) {
            /*$locationProvider.html5Mode({
                enabled: true,
                requireBase: false
            });*/

            $routeProvider.when('/clients', {
                templateUrl: 'views/clients.html',
                controller: 'ClientController'
            });

            $routeProvider.when('/newclient',{
                templateUrl: 'views/client.html',
                controller: 'NewClientController'
            });

            $routeProvider.when('/editclient/:id',{
                templateUrl: 'views/client.html',
                controller: 'EditClientController'
            });

            $routeProvider.otherwise({redirectTo: '/clients'});
        });
})();