angular.module("QBMS.controllers")

    .controller("StartExamingController", function ($scope, toaster, Service, $stateParams, $interval) {
        //参数定义
        $scope.paper = $stateParams.paper;
        $scope.canCommit = true;
        $scope.hasCommited = false;
        var useredTime = parseInt((new Date().getTime() - $scope.paper.startTime) / 1000);
        var canUseTime = $scope.paper.totalTime * 60;
        $scope.leftTime = canUseTime - useredTime;
        $scope.time = {
            getLeftTime: function () {
                return parseInt($scope.leftTime / 60);
            }
        }

        $scope.finishedExam = function () {
            Service.commitUserPaper.modify({id: $scope.paper.id}, $scope.paper, function (data) {
                if (data.result == true) {
                    $scope.hasCommited = true;
                    toaster.pop('success', data.message);
                } else {
                    toaster.pop('error', data.message);
                }
            });
        }
        if ($scope.leftTime > 0) {
            $interval(function () {
                --$scope.leftTime;
                if (++useredTime >= canUseTime) {
                    $scope.canCommit = false;
                }
            }, 1000)
        }

    })