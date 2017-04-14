angular.module("QBMS.login", ['toaster', 'ngAnimate'])
    .controller("LoginController", function ($rootScope, $window, $scope, $http, toaster) {
        $scope.user = {
            username: '',
            password: '',
        };
        $scope.login = function () {
            $http.post("/pc/login", $.param($scope.user), {
                headers: {
                    "content-type": "application/x-www-form-urlencoded"
                }
            })
                .then(function (data) {
                    if (data.data.result == true) {
                        $rootScope.lockScreen = false;
                        toaster.pop('success', '登陆成功');
                        $window.location.href = "/index.html";
                        // $location.path("/index.html");
                        // $http.get("/home").then(function (data) {
                        //     var d = data;
                        // });
                    } else {
                        toaster.pop("error", data.data.message);
                    }
                })
                .catch(function (data) {
                    toaster.pop("error", "登录出错");
                })
        }
    })
