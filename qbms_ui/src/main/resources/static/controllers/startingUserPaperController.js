angular.module("QBMS.controllers")

    .controller("StartingUserPaperController", function ($filter, $scope, $stateParams, Service, toaster, $state) {
        //定义变量
        $scope.userPapers = [];
        $scope.user = {};
        $scope.selectedRow = undefined;
        //数据表格设置
        var columnDefs = [
            {headerName: "编号", field: "userPaperId", pinned: 'left'},
            {headerName: "名称", field: "name"},
            {
                headerName: "开始时间", field: "startTime", cellRenderer: function (param) {
                return $filter('myDate')(param.value);
            }
            },
            {headerName: "总用时(分钟)", field: "totalTime"},
            {
                headerName: "剩余时间(分钟)", field: "totalTime", cellRenderer: function (param) {
                return $filter('leftTime')(param.data);
            }
            },
            {headerName: "出题人", field: "managerId"},
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
        function onSelectionChanged() {
            $scope.selectedRow = $scope.gridOptions.api.getSelectedRows()[0];
            $scope.$apply();
        }

        //数据查询
        queryStartingUserPapers();
        //开始考试
        $scope.startExaming = function () {
            $state.go("userpaper/startExaming", {'paper':$scope.selectedRow});
        }
        //查询正在进行中的考试
        function queryStartingUserPapers() {
            Service.userpapers.query({status: 2}, function (data) {
                if (data.result == undefined) {
                    $scope.userPapers = data;
                    $scope.gridOptions.api.setRowData($scope.userPapers);
                }
            })
        }

    })