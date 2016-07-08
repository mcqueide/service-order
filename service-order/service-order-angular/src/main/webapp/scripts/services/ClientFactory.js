angular.module('serviceorderangular').factory('ClientResource', function($resource){
    var resource = $resource('http://localhost:8080/service-order-rest/rest/client/:ClientId',{ClientId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});