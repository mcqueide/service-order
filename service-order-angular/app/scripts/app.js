/**
 * Created by mcqueide on 10/12/16.
 */
'use strict';

angular.module('serviceOrder',['ngRoute','ngResource'])
    .config(function ($routeProvider,$locationProvider) {
        $locationProvider.html5Mode({
            enabled: true,
            requireBase: false
        });

        $routeProvider.when('/clients', {
            templateUrl: 'views/clients.html',
            controller: 'ClientController'
        });

        $routeProvider.otherwise({redirectTo: '/clients'});
    });