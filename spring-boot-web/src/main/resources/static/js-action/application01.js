/**
 * Created by sylvanasp on 2016/7/28.
 */
var application01 = angular.module('application01',['ngRoute']);

application01.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/operation',{
        controller:'application01Controller',
        templateUrl:'view3.html',
    });
}]);
