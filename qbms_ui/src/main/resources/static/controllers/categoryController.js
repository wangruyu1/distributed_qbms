angular.module("QBMS.controllers",[])

    .controller("CategoryController", function ($scope, Service, $uibModal) {
        $scope.categories = [];
        $scope.selectedRow = undefined;
        var columnDefs = [
            {headerName: "编号", field: "categoryId", pinned: 'left'},
            {headerName: "类型", field: "name"},
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
                $scope.gridOptions.columnApi.setColumnPinned('description', 'left');
            },
        };

        //查询分类
        queryCategories();

        function onSelectionChanged() {
            $scope.selectedRow = $scope.gridOptions.api.getSelectedRows()[0];
            $scope.$apply();
        }

        //查询分类
        function queryCategories() {
            Service.paperCategories.query(function (data) {
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
                controller: 'addCategoryCtrl',
                scope: $scope,
                resolve: {}
            });
        }
        //修改分类
        $scope.modifyCategory = function () {
            var modalInstance = $uibModal.open({
                templateUrl: 'modifyCategory.html',
                controller: 'modifyCategoryCtrl',
                resolve: {
                    selectedRow: $scope.selectedRow,
                }
            });
        }
        //删除分类
        $scope.deleteCategory = function () {
            var modalInstance = $uibModal.open({
                templateUrl: 'deleteCategory.html',
                controller: 'deleteCategoryCtrl',
                resolve: {
                    selectedRow: $scope.selectedRow,
                }
            });
        }

    })
    .controller("addCategoryCtrl", function ($scope, Service, $uibModalInstance, toaster, $state) {
        $scope.category = {
            name: '',
            description: '',
        };
        $scope.cancel = function () {
            $uibModalInstance.dismiss();
        }
        $scope.ok = function () {
            Service.paperCategory.save({}, $scope.category, function (data) {
                if (data.result == false) {
                    toaster.pop('error', '添加分类失败');
                } else {
                    toaster.pop('success', '添加分类成功');
                    $state.reload();
                    $scope.cancel();
                }
            });
        }
    })
    .controller("modifyCategoryCtrl", function ($scope, Service, $uibModalInstance, toaster, $state, selectedRow) {
        $scope.category = selectedRow;

        $scope.cancel = function () {
            $uibModalInstance.dismiss();
        }
        $scope.ok = function () {
            Service.paperCategory.modify({id: $scope.category.categoryId}, $scope.category, function (data) {
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
    .controller("deleteCategoryCtrl", function ($scope, Service, $uibModalInstance, toaster, $state, selectedRow) {
        $scope.category = selectedRow;

        $scope.cancel = function () {
            $uibModalInstance.dismiss();
        }
        $scope.ok = function () {
            Service.paperCategory.delete({id: $scope.category.categoryId}, function (data) {
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
