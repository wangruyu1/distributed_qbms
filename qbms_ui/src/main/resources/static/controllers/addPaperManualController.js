angular.module("QBMS.controllers")
    .controller("AddPaperManualController", function ($scope, Service, $uibModal, syncService, toaster) {
        $scope.subjects = [];
        $scope.selectedRow = undefined;
        $scope.currentBigSubject = 1;
        $scope.currentLittleSubject = 1;
        $scope.difficulties = [];
        $scope.categories = [];
        $scope.paperCategories = [];
        $scope.categoryFlag = true;
        $scope.difficultyFlag = true;
        $scope.rowNum = 1;
        // $scope.user = {};
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
            content: '',
            answer: '',
            firstSubject: false,
            userId: -1,

        };
        var columnDefs = [
            {
                headerName: "编号", field: "subjectId", pinned: 'left', cellRenderer: function () {
                return $scope.rowNum++;
            }
            },
            {headerName: "名称", field: "name"},
            {
                headerName: "分类",
                field: "categoryName",
                // cellRenderer: categoryFilter,
            },
            {
                headerName: "困难等级",
                field: "difficultyName",
                // cellRenderer: difficultyFilter,
            },
        ];
        $scope.gridOptions = {
            columnDefs: columnDefs,
            rowData: $scope.subjects,
            enableFilter: true,
            enableSorting: true,
            rowSelection: 'single',
            onSelectionChanged: onSelectionChanged,
            enableColResize: true,
            onGridReady: function (event) {
                $scope.gridOptions.api.sizeColumnsToFit();
            },
        };
        queryUser();
        querySubjects();
        querySubjectCategories();
        queryPaperCategories();
        querySubjectDifficulties();

        $scope.finishedPaperBaseInfo = function () {
            Service.paper_baseinfo.save({}, $scope.paper, function (data) {
                if (data.result == undefined) {
                    var userName = $scope.paper.userName;
                    $scope.paper = data;
                    $scope.paper.userName = userName;
                    toaster.pop('success', "添加试卷基本信息成功");
                } else {
                    toaster.pop('error', data.message);
                }
            });
        }

        $scope.addContent = function () {
            if ($scope.paper.paperId == undefined || $scope.paper.paperId == "") {
                toaster.pop('error', '请先设置试卷基本信息');
                return;
            }
            if ($scope.currentLittleSubject == 1) {
                $scope.subject.firstSubject = true;
            } else {
                $scope.subject.firstSubject = false;
            }
            $scope.subject.paperId = $scope.paper.paperId;
            Service.addContent.save({id: $scope.subject.paperId}, $scope.subject, function (data) {
                var message = '添加试题第' + $scope.currentBigSubject + '大题第' + $scope.currentLittleSubject + '小题成功';
                if (data.result == true) {
                    $scope.currentLittleSubject = $scope.currentLittleSubject + 1;
                    $scope.subject.content = "";
                    $scope.subject.answer = "";
                    $scope.subject.name = "";
                    toaster.pop('success', message);
                } else {
                    toaster.pop('error', data.message);
                }
            });
        }

        $scope.getSubjectDetail = function () {
            var modal = $uibModal.open({
                templateUrl: 'subjectDetail.html',
                controller: 'subjectDetailCtrl',
                resolve: {
                    selectedRow: $scope.selectedRow,
                }
            });
        }

        $scope.addToPaper = function () {
            angular.forEach($scope.subjects, function (value) {
                if (value.subjectId == $scope.selectedRow.subjectId) {
                    $scope.subject.name = value.name;
                    $scope.subject.categoryId = value.subjectCategoryId;
                    $scope.subject.difficultyId = value.subjectDifficultyId;
                    $scope.subject.content = value.content;
                    $scope.subject.answer = value.answer;
                    $scope.subject.userId = value.userId;
                    Service.user.get(function (data) {
                        $scope.subject.userName = data.name;
                    })
                }
            })
        }
        $scope.doNextSubject = function () {
            $scope.currentBigSubject = $scope.currentBigSubject + 1;
            $scope.currentLittleSubject = 1;
        }
        function setWidthAndHeight() {
            var eGridDiv = document.querySelector('#subjectGrid');
            eGridDiv.style.width = 480;
            eGridDiv.style.height = 800;
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

        function categoryFilter(row) {
            if ($scope.categories == undefined || $scope.categories.length == 0) {
                return 'undefined';
            }
            for (var i = 0; i < $scope.categories.length; i++) {
                if ($scope.categories[i].subjectCategoryId == row.data.subjectCategoryId) {
                    return $scope.categories[i].name;
                }
            }
            return $scope.result;
        }

        function difficultyFilter(row) {
            if ($scope.difficulties == undefined || $scope.difficulties.length == 0) {
                return 'undefined';
            }
            for (var i = 0; i < $scope.difficulties.length; i++) {
                if ($scope.difficulties[i].subjectId == row.data.subjectDifficultyId) {
                    return $scope.difficulties[i].name;
                }
            }
            return '';
        }

        function onSelectionChanged() {
            $scope.selectedRow = $scope.gridOptions.api.getSelectedRows()[0];
            $scope.$apply();
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

        function querySubjects() {
            Service.subjects.query(function (data) {
                if (data.result == undefined) {
                    $scope.subjects = data;
                    $scope.gridOptions.api.setRowData(data);
                }
            });
        }
    })
    .controller("subjectDetailCtrl", function ($scope, $uibModalInstance, selectedRow) {
        $scope.subject = selectedRow;

        $scope.ok = function () {
            $scope.cancel();
        }
        $scope.cancel = function () {
            $uibModalInstance.dismiss();
        }
    })
