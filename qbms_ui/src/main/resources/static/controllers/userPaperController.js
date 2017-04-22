angular.module("QBMS.controllers")

    .controller("UserPaperController", function ($scope, Service, toaster) {
        $scope.papers = [];
        $scope.user = {};
        $scope.selectedRow = undefined;
        var columnDefs = [
            {headerName: "编号", field: "id", pinned: 'left'},
            {headerName: "名称", field: "name"},
            {headerName: "开始时间", field: "startTime"},
        ];
        $scope.gridOptions = {
            columnDefs: columnDefs,
            rowData: $scope.papers,
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

        //初始化数据
        queryExamedPapers();


        function queryExamedPapers() {
            Service.userpapers.query({status: 3}, function (data) {
                if (data.result == undefined) {
                    $scope.papers = data;
                } else {
                    toaster.pop('error', data.message);
                }
            })
        }

        function queryUser() {
            Service.user.get(function (data) {
                if (data.result == undefined) {
                    $scope.user = data;
                }
            });
        }

    })