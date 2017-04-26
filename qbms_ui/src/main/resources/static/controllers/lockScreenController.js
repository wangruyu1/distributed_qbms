angular.module("QBMS")
    .controller("LockScreenController", function ($rootScope, $scope, Service, toaster) {
        $rootScope.lockScreen = true;
        $scope.password = '';
        $scope.user = {};

        queryUser();

        $scope.ok = function () {
            Service.checkpassword.save({
                password: $scope.password
            }, function (data) {
                if (data.code == 401) {
                    window.location.href = "/ui/login.html";
                } else if (data.code == 402) {
                    toaster.pop("error", data.message);
                } else if ($scope.user.type == 2) {
                    window.top.location.href = "/ui/index.html";
                } else if ($scope.user.type == 1) {
                    window.top.location.href = "/ui/index_manager.html";
                }
            })
        }

        function queryUser() {
            Service.user.get(function (data) {
                if (data.result == undefined) {
                    $scope.user = data;
                }
            })
        }
    })
