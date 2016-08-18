/**
 * Created by sylvanasp on 2016/8/18.
 */
actionApp.controller('helloCtrl', ['$scope', function ($scope) {
    $scope.hello = 'Hello,World!';
}]);

actionApp.controller('registerCtrl', ['$scope', '$location', '$http', function ($scope, $location, $http) {

    $scope.userdata = {};

    $scope.registerError = {};

    $scope.registerSubmit = function () {
        if ($scope.registerForm.$invalid) {
            $scope.registerError = '注册信息有误!';
            $location.path('/register');
        } else {
            $location.path('/hello');
        }
    }

}]);

actionApp.directive('compare', function () {

    var o = {};

    o.strict = 'AE';
    o.scope = {
        orgText: '=compare'
    };

    o.require = 'ngModel';
    o.link = function (scope, element, attr, ctrl) {
        ctrl.$validators.compare = function (v) {
            return v == scope.orgText;
        };
        scope.$watch('orgText', function () {
            ctrl.$validate();
        });
    };

    return o;
});