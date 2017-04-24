// get ag-Grid to create an Angular module and register the ag-Grid directive
agGrid.initialiseAgGridWithAngular1(angular);

// create your module with ag-Grid as a dependency
angular.module("QBMS")

    .controller("IndexController", function ($rootScope, $scope, toaster, $window, Service, $uibModal, $interval) {

        $scope.futureUserPapers = [];
        $scope.startingUserPapers = [];

        // connect();
        var stompClient = null;

        // function connect() {
        //     var socket = new SockJS('/endpointWisely'); //1
        //     stompClient = Stomp.over(socket);//2
        //     stompClient.connect({}, function (frame) {//3
        //         // setConnected(true);
        //         console.log('开始进行连接Connected: ' + frame);
        //         stompClient.subscribe('/topic/getResponse', function (respnose) { //4
        //             // showResponse(JSON.parse(respnose.body).responseMessage);
        //             $scope.exam = JSON.parse(respnose.body).responseMessage;
        //         });
        //     });
        // }
        // function disconnect() {
        //     if (stompClient != null) {
        //         stompClient.disconnect();
        //     }
        //     // setConnected(false);
        //     console.log("Disconnected");
        // }
        // function sendName() {
        //     var name = $('#name').val();
        //     stompClient.send("/welcome", {}, JSON.stringify({'name': name}));//5
        // }

        $scope.user = {};
        $scope.menus = [];
        $scope.currentTime = new Date();

        queryUser();
        queryFutureUserPapers();
        queryStartingUserPapers();
        //获取用户信息
        function queryUser() {
            Service.user.get(function (data) {
                if (data.menus == undefined) {
                    $window.top.location.href = "/login.html";
                } else if (data.result == undefined) {
                    $scope.user = data;
                    $scope.menus = data.menus;
                } else {
                    toaster.pop('error', data.message);
                }
            });
        }

        //轮训查询
        function queryFutureUserPapers() {
            $interval(function () {
                Service.userpapers.query({status: 1}, function (data) {
                    if (data.result == undefined) {
                        $scope.futureUserPapers = data;
                    }
                })
            }, 10000);
        }

        function queryStartingUserPapers() {
            $interval(function () {
                Service.userpapers.query({status: 2}, function (data) {
                    if (data.result == undefined) {
                        $scope.startingUserPapers = data;
                    }
                })
            }, 10000);
        }

        $scope.logout = function () {
            Service.logout.get(function (data) {
                var rtnData = angular.fromJson(data);
                if (rtnData.result == true) {
                    window.location.href = "/ui/login.html";
                } else {
                    toaster.pop('error', '注销失败')
                }
            })
        }

        $scope.modifyPassword = function () {
            $uibModal.open({
                templateUrl: 'modifyPassword.html',
                controller: 'modifyPasswordCtrl',
                resolve: {
                    user: $scope.user,
                }
            })
            ;
        }

        $scope.lockScreen = function () {
            $window.top.location.href = "pages/lockscreen/lockScreen.html";
        }

    })
    .controller('modifyPasswordCtrl', function ($scope, toaster, $uibModalInstance, Service) {
        $scope.oldPsw = '';
        $scope.oldPswFlag = false;
        $scope.newPsw = '';
        $scope.reNewPsw = '';
        $scope.finishOldPsw = false;
        $scope.checkOldPsw = function () {
            $scope.finishOldPsw = true;
            Service.checkpassword.save({password: $scope.oldPsw}, {}, function (data) {
                if (data.result == true) {
                    $scope.oldPswFlag = true;
                } else {
                    $scope.oldPswFlag = false;
                }
            })
        }
        $scope.wipeOffTip = function () {
            $scope.finishOldPsw = false;
        }
        $scope.cancel = function () {
            $uibModalInstance.dismiss();
        }
        $scope.ok = function () {
            Service.modifyPassword.put({password: $scope.newPsw}, {}, function (data) {
                if (data.result == true) {
                    toaster.pop('success', data.message);
                    $scope.cancel();
                } else {
                    toaster.pop('error', data.message);
                }
            })
        }
    })

