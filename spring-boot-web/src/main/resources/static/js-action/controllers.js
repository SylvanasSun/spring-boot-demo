/**
 * Created by sylvanasp on 2016/7/28.
 */
// 定义控制器
// 定义控制器View1Controller,并注入$rootScope,$scop,$http.
actionApp.controller('View1Controller', ['$rootScope', '$scope', '$http',
    function ($rootScope, $scope, $http) {
        /*
         * 通过$scope.$on监听$viewContentLoaded事件,
         * 可以在页面内容加载完成后进行一些操作.
         * */
        $scope.$on('$viewContentLoaded', function () {
            console.log('页面加载完成!');
        });

        /*
         * 在$scope中定义一个search函数,在页面上可以通过ng-click调用.
         * 通过$scope.personName获取页面定义的ng-module="personName"的值.
         * 使用$http.get向服务器地址serach发送get请求.
         * 使用params设置请求时传递的参数.
         * 使用success函数作为请求成功后的回调函数.
         * 将服务端返回的数据data通过$scope.person赋给模型person,这样页面视图上
         * 可以通过{{person.name}}..调用获得值,且模型person值改变后,视图是会自动更新的。
         * */
        $scope.search = function () {
            personName = $scope.personName;
            $http.get('search', {
                params: {personName: personName}
            }).success(function (data) {
                $scope.person = data;
            });
        };

    }]);

// 定义控制器View2Controller,并注入$rootScope,$scope.
actionApp.controller('View2Controller', ['$rootScope', '$scope',
    function ($rootScope, $scope) {
        $scope.$on('viewContentLoaded', function () {
            console.log('页面加载完成!');
        });
    }
]);
