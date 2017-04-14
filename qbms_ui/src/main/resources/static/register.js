/**
 * Created by wangruyu on 2017/3/22.
 */
angular.module("QBMS.register", ['toaster', 'ngAnimate'])
    .controller("RegisterController", function ($window, $scope, $http, toaster) {
        $scope.user = {
            username: '',
            password: '',
        };
        $scope.login = function () {
            $http.post("/register", {username: user.username, password: user.password})
                .then(function (data) {
                    if (data.data.result == true) {
                        toaster.pop('success', '注册成功');
                        $window.location.href = "/login.html";
                        // $location.path("/index.html");
                        // $http.get("/home").then(function (data) {
                        //     var d = data;
                        // });
                    } else {
                        toaster.pop("error", data.data.message);
                    }
                })
                .catch(function (data) {
                    toaster.pop("error", "注册出错");
                })
        }
    })
