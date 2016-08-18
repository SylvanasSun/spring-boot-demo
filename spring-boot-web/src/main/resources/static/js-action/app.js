/**
 * Created by sylvanasp on 2016/7/28.
 */
// 定义AngularJS模块和路由

// 定义模块actionApp,并依赖于路由模块ngRoute.
var actionApp = angular.module('actionApp', ['ngRoute']);

// 配置路由,并注入$routeProvider用来配置路由.
actionApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/oper', {
        controller:'View1Controller',
        templateUrl:'views/view1.html',
    }).when('/directive',{
        controller:'View2Controller',
        templateUrl:'views/view2.html',
    }).when('/hello',{
        controller : 'helloCtrl',
        templateUrl : 'views/hello.html'
    }).when('/register',{
        controller : 'registerCtrl',
        templateUrl : 'views/register.html'
    });
}]);