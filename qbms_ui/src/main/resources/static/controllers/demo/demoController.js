angular.module("QBMS.demo", [])

    .controller("DemoController", function ($scope, $http) {
        $scope.ok = function () {
            $http.get("/demo")
                .then(function (data) {
                    $scope.data = data;
                }, function (errorData) {
                    $scope.errorData = data;
                });
        }
    })