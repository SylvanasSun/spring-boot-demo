/**
 * Created by sylvanasp on 2016/7/28.
 */
application01.controller('application01Controller', ['$rootScope', '$scope', '$http',
    function ($rootScope, $scope, $http) {
        $scope.$on('viewContentLoaded', function () {
            console.log('页面加载完成!');
        });
        $scope.findAnimal = function () {
            animalName = $scope.animalName;
            $http.get('findAnimal', {
                params: {animalName: animalName}
            }).success(function (data) {
                $scope.animal = data;
            });
        };
    }
]);