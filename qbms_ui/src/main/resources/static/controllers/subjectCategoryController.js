angular.module("QBMS.controllers")

    .controller("SubjectCategoryController", function ($scope, Service, $uibModal) {
        $scope.categories = [];
        $scope.selectedRow = undefined;
        var columnDefs = [
            {headerName: "编号", field: "subjectCategoryId", pinned: 'left'},
            {headerName: "名称", field: "name"},
            {headerName: "描述", field: "description"},
        ];
        $scope.gridOptions = {
            columnDefs: columnDefs,
            rowData: $scope.categories,
            enableSorting: true,
            rowSelection: 'single',
            onSelectionChanged: onSelectionChanged,
            enableColResize: true,
            onGridReady: function (event) {
                $scope.gridOptions.api.sizeColumnsToFit();
            },
        };

        //分类查询
        queryCategories();

        function onSelectionChanged() {
            $scope.selectedRow = $scope.gridOptions.api.getSelectedRows()[0];
            $scope.$apply();
        }


        //????
        function queryCategories() {
            Service.subjectCategories.query(function (data) {
                if (data.result == undefined) {
                    $scope.categories = data;
                    $scope.gridOptions.api.setRowData($scope.categories);
                }
            })
        }

        //添加分类
        $scope.addCategory = function () {
            var modalInstance = $uibModal.open({
                templateUrl: 'addCategory.html',
                controller: 'addSubjectCategoryCtrl',
                scope: $scope,
                resolve: {}
            });
        }
        //????
        $scope.modifyCategory = function () {
            var modalInstance = $uibModal.open({
                templateUrl: 'modifyCategory.html',
                controller: 'modifySubjectCategoryCtrl',
                resolve: {
                    selectedRow: $scope.selectedRow,
                }
            });
        }
        //????
        $scope.deleteCategory = function () {
            var modalInstance = $uibModal.open({
                templateUrl: 'deleteCategory.html',
                controller: 'deleteSubjectCategoryCtrl',
                resolve: {
                    selectedRow: $scope.selectedRow,
                }
            });
        }

    })
    .controller("addSubjectCategoryCtrl", function ($scope, Service, $uibModalInstance, toaster, $state) {
        $scope.category = {
            name: '',
            description: '',
        };
        $scope.cancel = function () {
            $uibModalInstance.dismiss();
        }
        $scope.ok = function () {
            Service.subjectCategory.save({}, $scope.category, function (data) {
                if (data.result == false) {
                    toaster.pop('error', data.message);
                } else {
                    toaster.pop('success', data.message);
                    $state.reload();
                    $scope.cancel();
                }
            });
        }
    })
    .controller("modifySubjectCategoryCtrl", function ($scope, Service, $uibModalInstance, toaster, $state, selectedRow) {
        $scope.category = selectedRow;

        $scope.cancel = function () {
            $uibModalInstance.dismiss();
        }
        $scope.ok = function () {
            Service.subjectCategory.modify({id: $scope.category.subjectCategoryId}, $scope.category, function (data) {
                if (data.result == false) {
                    toaster.pop('error', data.message);
                } else {
                    toaster.pop('success', data.message);
                    $state.reload();
                    $scope.cancel();
                }
            });
        }
    })
    .controller("deleteSubjectCategoryCtrl", function ($scope, Service, $uibModalInstance, toaster, $state, selectedRow) {
        $scope.category = selectedRow;

        $scope.cancel = function () {
            $uibModalInstance.dismiss();
        }
        $scope.ok = function () {
            Service.subjectCategory.delete({id: $scope.category.subjectCategoryId}, function (data) {
                if (data.result == false) {
                    toaster.pop('error', data.message);
                } else {
                    toaster.pop({
                        type: 'success',
                        body: data.message,
                        timeout: 3000,
                    });
                    $state.reload();
                    $scope.cancel();
                }
            });
        }
    })
