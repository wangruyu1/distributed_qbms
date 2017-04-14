angular.module("QBMS.controllers")

    .controller("SubjectDifficultyController", function ($scope, $uibModal, Service) {
            $scope.subjectDifficulties = [];
            $scope.selectedRow = undefined;
            $scope.subjectDifficulties = [];
            var columnDefs = [
                {headerName: "编号", field: "subjectId", pinned: 'left'},
                {headerName: "类型", field: "name"},
                {headerName: "描述", field: "description"},
            ];
            $scope.gridOptions = {
                columnDefs: columnDefs,
                rowData: $scope.subjectDifficulties,
                enableSorting: true,
                rowSelection: 'single',
                onSelectionChanged: onSelectionChanged,
                enableColResize: true,
                onGridReady: function (event) {
                    $scope.gridOptions.api.sizeColumnsToFit();
                },
            };

            //查询等级
            querySubjectDifficulties();

            function onSelectionChanged() {
                $scope.selectedRow = $scope.gridOptions.api.getSelectedRows()[0];
                $scope.$apply();
            }

            function querySubjectDifficulties() {
                Service.subjectDifficulties.query(function (data) {
                    if (data.result == undefined) {
                        $scope.subjectDifficulties = data;
                        $scope.gridOptions.api.setRowData(data);
                    } else {
                        toaster.pop('error', data.message);
                    }
                });
            }

            $scope.addSubjectDifficulty = function () {
                var modalInstance = $uibModal.open({
                    templateUrl: 'addSubjectDifficulty.html',
                    controller: 'addSubjectDifficultyCtrl',
                    resolve: {}
                });
            }
            $scope.modifySubjectDifficulty = function () {
                var modalInstance = $uibModal.open({
                    templateUrl: 'modifySubjectDifficulty.html',
                    controller: 'modifySubjectDifficultyCtrl',
                    resolve: {
                        selectedRow: $scope.selectedRow,
                    }
                })
            };
            $scope.deleteSubjectDifficulty = function () {
                var modalInstance = $uibModal.open({
                        templateUrl: 'deleteSubjectDifficulty.html',
                        controller: 'deleteSubjectDifficultyCtrl',
                        resolve: {
                            selectedRow: $scope.selectedRow,
                        }
                    }
                );
            }

        }
    )
    .controller("addSubjectDifficultyCtrl", function ($scope, Service, $uibModalInstance, toaster, $state) {
        $scope.subjectDifficulty = {
            name: '',
            description: '',
        };
        $scope.ok = function () {
            Service.subjectDifficulty.save({}, $scope.subjectDifficulty, function (data) {
                if (data.result == true) {
                    toaster.pop('success', data.message);
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
    })
    .controller("modifySubjectDifficultyCtrl", function ($scope, Service, $uibModalInstance, toaster, $state, selectedRow) {
        $scope.subjectDifficulty = selectedRow;

        $scope.ok = function () {
            Service.subjectDifficulty.modify({id: $scope.subjectDifficulty.subjectId}, $scope.subjectDifficulty, function (data) {
                if (data.result == true) {
                    toaster.pop('success', data.message);
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
    })
    .controller("deleteSubjectDifficultyCtrl", function ($scope, Service, $uibModalInstance, toaster, $state, selectedRow) {
        $scope.subjectDifficulty = selectedRow;

        $scope.ok = function () {
            Service.subjectDifficulty.delete({id: $scope.subjectDifficulty.subjectId}, function (data) {
                if (data.result == true) {
                    toaster.pop('success', data.message);
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
    })
