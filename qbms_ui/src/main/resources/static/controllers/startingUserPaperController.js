angular.module("QBMS.controllers")

    .controller("StartingUserPaperController", function ($scope, Service, toaster) {
        $scope.userPapers = {};

        var columnDefs = [
            {headerName: "编号", field: "id", pinned: 'left'},
            {headerName: "名称", field: "name"},
            {
                headerName: "开始时间", field: "startTime", cellRenderer: function (param) {
                return $filter('myDate')(param.value);
            }
            },
            {headerName: "总用时", field: "totalTime"},
            {headerName: "出题人", field: "managerName"},
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
        queryFutureUserPapers();

        function queryFutureUserPapers() {
            Service.userpapers.query({status: 1}, function (data) {
                if (data.result == undefined) {
                    $scope.userPapers = data;
                    $scope.gridOptions.api.setRowData($scope.userPapers);
                }
            })
        }

    })