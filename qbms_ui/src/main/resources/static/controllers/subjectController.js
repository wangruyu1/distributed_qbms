angular.module("QBMS.controllers")

    .controller("SubjectController", function ($scope, Service, $uibModal) {
        $scope.subjects = [];
        $scope.selectedRow = undefined;
        $scope.categories = [];
        $scope.difficulties = [];
        $scope.subject = {
            subjectId: '',
            name: '',
            subjectCategoryId: '',
            subjectDifficultyId: '',
            userId: '',
            content: '',
            answer: '',

        };
        var columnDefs = [
            {headerName: "编号", field: "subjectId", pinned: 'left'},
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
            // paginationAutoPageSize: true,
            pagination: true,
        };
        querySubjectCategories();
        querySubjectDifficulties();
        function setWidthAndHeight() {
            var eGridDiv = document.querySelector('#subjectGrid');
            eGridDiv.style.width = 480;
            eGridDiv.style.height = 800;
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
            return '';
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

        function autoSizeAll() {
            var allColumnIds = [];
            columnDefs.forEach(function (columnDef) {
                allColumnIds.push(columnDef.field);
            });
            $scope.gridOptions.columnApi.autoSizeColumns(allColumnIds);
        }

        //????
        querySubjects();

        function onSelectionChanged() {
            $scope.selectedRow = $scope.gridOptions.api.getSelectedRows()[0];
            $scope.$apply();
        }

        $scope.getDetail = function () {
            var modal = $uibModal.open({
                templateUrl: 'subjectDetail.html',
                controller: 'subjectDetailCtrl',
                resolve: {
                    selectedRow: $scope.selectedRow,
                }
            });
        }

        $scope.addSubject = function () {
            var modal = $uibModal.open({
                templateUrl: 'addSubject.html',
                controller: 'addSubjectCtrl',
                resolve: {}
            });
        }
        $scope.modifySubject = function () {
            var modal = $uibModal.open({
                templateUrl: 'modifySubject.html',
                controller: 'modifySubjectCtrl',
                resolve: {
                    selectedRow: $scope.selectedRow,
                }
            });
        }
        $scope.deleteSubject = function () {
            var modal = $uibModal.open({
                templateUrl: 'deleteSubject.html',
                controller: 'deleteSubjectCtrl',
                resolve: {
                    selectedRow: $scope.selectedRow,
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

        function querySubjectCategories() {
            Service.subjectCategories.query(function (data) {
                if (data.result == undefined) {
                    $scope.categories = data;
                }
            });
        }

        function querySubjectDifficulties() {
            Service.subjectDifficulties.query(function (data) {
                if (data.result == undefined) {
                    $scope.difficulties = data;
                }
            });
        }
    })
    .controller("addSubjectCtrl", function ($scope, toaster, Service, $uibModalInstance, $state) {
        $scope.subject = {
            subjectId: '',
            name: '',
            subjectCategoryId: '',
            subjectDifficultyId: '',
            userId: '',
            content: '',
            answer: '',

        };
        $scope.category = {};
        $scope.difficuty = {};
        querySubjectCategories();
        querySubjectDifficulties();
        $scope.ok = function () {
            $scope.subject.subjectCategoryId = $scope.category.subjectCategoryId;
            $scope.subject.subjectDifficultyId = $scope.difficulty.subjectId;
            Service.subject.save($scope.subject, function (data) {
                if (data.result == true) {
                    toaster.pop('sccess', data.message);
                    $scope.cancel();
                    $state.reload();
                } else {
                    toaster.pop('error', data.message);
                }
            });
        }
        $scope.cancel = function () {
            $uibModalInstance.dismiss();
        }

        function querySubjectCategories() {
            Service.subjectCategories.query(function (data) {
                if (data.result == undefined) {
                    $scope.categories = data;
                }
            });
        }

        //????????
        function querySubjectDifficulties() {
            Service.subjectDifficulties.query(function (data) {
                if (data.result == undefined) {
                    $scope.difficulties = data;
                }
            });
        }

    })
    .controller("modifySubjectCtrl", function ($scope, toaster, Service, $state, $uibModalInstance, selectedRow) {
        $scope.subject = selectedRow;
        $scope.categories = [];
        $scope.difficulties = [];
        $scope.category = {
            subjectCategoryId: $scope.subject.subjectCategoryId,
        };
        $scope.difficulty = {
            subjectId: $scope.subject.subjectDifficultyId,
        };

        querySubjectCategories();
        querySubjectDifficulties();
        $scope.basic_ok = function () {
            $scope.subject.subjectCategoryId = $scope.category.subjectCategoryId;
            $scope.subject.subjectDifficultyId = $scope.difficulty.subjectId;
            Service.subjectBasic.modify({id: $scope.subject.subjectId}, $scope.subject, function (data) {
                if (data.result == true) {
                    toaster.pop('sccess', data.message);
                    $state.reload();
                } else {
                    toaster.pop('error', data.message);
                }
            })
        }
        //??????
        $scope.content_ok = function () {
            Service.subjectContent.modify({id: $scope.subject.subjectId}, $scope.subject, function (data) {
                if (data.result == true) {
                    toaster.pop('sccess', data.message);
                    $state.reload();
                } else {
                    toaster.pop('error', data.message);
                }
            })
        }
        //??????
        $scope.answer_ok = function () {
            Service.subjectAnswer.modify({id: $scope.subject.subjectId}, $scope.subject, function (data) {
                if (data.result == true) {
                    toaster.pop('sccess', data.message);
                    $state.reload();
                } else {
                    toaster.pop('error', data.message);
                }
            })
        }
        $scope.ok = function () {
            $scope.cancel();
            $state.reload();
        }
        $scope.cancel = function () {
            $uibModalInstance.dismiss();
        }
        function querySubjectCategories() {
            Service.subjectCategories.query(function (data) {
                if (data.result == undefined) {
                    $scope.categories = data;
                }
            });
        }

        function querySubjectDifficulties() {
            Service.subjectDifficulties.query(function (data) {
                if (data.result == undefined) {
                    $scope.difficulties = data;
                }
            });
        }
    })
    .controller("deleteSubjectCtrl", function (Service, selectedRow, toaster, $scope, $uibModalInstance, $state) {
        $scope.subject = selectedRow;
        $scope.ok = function () {
            Service.subject.delete({id: selectedRow.subjectId}, function (data) {
                if (data.result == true) {
                    toaster.pop('success', data.message);
                    $scope.cancel();
                    $state.reload();
                } else {
                    toaster.pop('error', data.message);
                }
            })
        }
        $scope.cancel = function () {
            $uibModalInstance.dismiss();
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
