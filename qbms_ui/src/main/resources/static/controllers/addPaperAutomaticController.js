angular.module('QBMS.controllers')

    .controller('AddPaperAutomaticController', function ($scope, toaster, Service, $uibModal) {
        $scope.categories = [];
        $scope.difficulties = [];
        $scope.currentBigSubject = 1;
        $scope.content = "";
        $scope.answer = "";
        $scope.paper = {
            paperId: '',
            name: '',
            paperDifficultyId: '',
            paperCategoryId: '',
            userName: '',
            title: '',
            subjectId: '',
        };
        $scope.subject = {
            paperId: '',
            name: '',
            userName: '',
            categoryId: '',
            difficultyId: '',
            score: '',
            firstSubject: false,
            userId: -1,
            littleSubjectNum: undefined,
        };

        queryPaperCategories();
        querySubjectCategories();
        querySubjectDifficulties();
        queryUser();

        $scope.finishedPaperBaseInfo = function () {
            Service.paper_baseinfo.save({}, $scope.paper, function (data) {
                if (data.result == undefined) {
                    var userName = $scope.paper.userName;
                    $scope.paper = data;
                    $scope.paper.userName = userName;
                    $scope.subject.paperId = data.paperId;
                    toaster.pop('success', "添加试卷基本信息成功");
                } else {
                    toaster.pop('error', data.message);
                }
            });
        }
        $scope.addSubject = function () {
            if ($scope.paper.paperId == undefined || $scope.paper.paperId == "") {
                toaster.pop('error', '请先设置试卷基本信息');
                return;
            }
            Service.addPaperAutomatic.save({id: $scope.subject.paperId}, $scope.subject, function (data) {
                if (data.result == true) {
                    toaster.pop('success', data.message);
                    Service.paper.get({id: $scope.paper.paperId}, function (data) {
                        if (data.result == undefined) {
                            $scope.subject.content = data.content;
                            $scope.subject.answer = data.answer;
                            $scope.currentBigSubject++;
                        } else {
                            toaster.pop('error', data.message);
                        }
                    })
                } else {
                    toaster.pop('error', data.message);
                }
            });
        }

        $scope.doNextSubject = function () {
            $scope.currentBigSubject++;
        }


        function queryUser() {
            Service.user.get(function (data) {
                if (data.result == undefined) {
                    $scope.user == data;
                    $scope.paper.userName = $scope.user.name;
                    $scope.subject.userId = $scope.user.userId;
                    $scope.subject.userName = $scope.user.name;
                } else {
                    toaster.pop('error', data.message);
                }
            });
        }

        function querySubjectCategories() {
            Service.subjectCategories.query(function (data) {
                if (data.result == undefined) {
                    $scope.categories = data;
                } else {
                    toaster.pop('error', data.message);
                }
                $scope.categoryFlag = false;
            }, function (errorData) {
                toaster.pop('error', errorData.message);
            });
        }

        function querySubjectDifficulties() {
            Service.subjectDifficulties.query(function (data) {
                if (data.result == undefined) {
                    $scope.difficulties = data;
                }
            });
        }

        function queryPaperCategories() {
            Service.paperCategories.query(function (data) {
                if (data.result == undefined) {
                    $scope.paperCategories = data;
                }
            });
        }
    })