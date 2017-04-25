angular.module("QBMS.controllers")

    .controller("ReviseUserPaperController", function ($filter, $scope, Service, $uibModal, toaster) {
        //变量定义
        $scope.userPapers = [];
        $scope.selectedRow = undefined;
        //数据显示表格设置
        var columnDefs = [
            {headerName: "编号", field: "userPaperId", pinned: 'left'},
            {headerName: "名称", field: "userPaperName"},
            {headerName: "标题", field: "userPaperTitle"},
            // {
            //     headerName: "创建时间", field: "createTime", cellRenderer: function (param) {
            //     return $filter('myDate')(param.value);
            // }
            // },
            {
                headerName: "开始时间", field: "startTime", cellRenderer: function (param) {
                return $filter('myDate')(param.value);
            }
            },
            {headerName: "总用时(分钟)", field: "totalTime"},
            {headerName: "用户名", field: "userName"},
        ];
        $scope.gridOptions = {
            columnDefs: columnDefs,
            rowData: $scope.userPapers,
            enableSorting: true,
            rowSelection: 'single',
            onSelectionChanged: onSelectionChanged,
            enableColResize: true,
            onGridReady: function (event) {
                $scope.gridOptions.api.sizeColumnsToFit();
            },
        };

        //初始化数据
        queryUserPapers();

        //函数定义
        $scope.correctUserPaper = function () {
            $uibModal.open({
                templateUrl: 'userPaperDetail.html',
                controller: 'userPaperDetailCtrl',
                size: 'lg',
                resolve: {
                    selectedRow: $scope.selectedRow,
                },
            });
        }
        function onSelectionChanged() {
            $scope.selectedRow = $scope.gridOptions.api.getSelectedRows()[0];
            $scope.$apply();
        }

        function queryUserPapers() {
            Service.commitedUserPapers.query(function (data) {
                if (data.result == undefined) {
                    $scope.userPapers = data;
                    $scope.gridOptions.api.setRowData($scope.userPapers);
                } else {
                    toaster('error', data.message);
                }
            });
        }
    })