/**
 * Created by mcqueide on 10/12/16.
 */
(function () {
    'use strict';

    angular.module('serviceOrder')
        .constant('baseURL', 'http://localhost:8080/service-order-rest/rest/')
        .service('clientService',['$resource','baseURL',function ($resource,baseURL){
            this.getClients = function () {
                return $resource(baseURL+'client/:id',null,{'update':{method:'PUT'}});
            };
        }]);
})();