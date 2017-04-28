angular.module("QBMS.controllers")

    .controller("UserPaperController", function ($scope, Service, toaster, $filter,$uibModal) {
        //变量定义
        $scope.papers = [];
        $scope.user = {};
        $scope.selectedRow = undefined;
        //数据表格显示设置
        var columnDefs = [
            {headerName: "编号", field: "userPaperId", pinned: 'left'},
            {headerName: "名称", field: "name"},
            {headerName: "标题", field: "title"},
            {
                headerName: "开始时间", field: "startTime", cellRenderer: function (param) {
                return $filter('myDate')(param.value);
            }
            },
            {headerName: "总用时(分钟)", field: "totalTime"},
            {headerName: "得分", field: "score"},
            {headerName: "批改人", field: "managerName"},
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

        //方法定义
        $scope.getPaperDetail = function () {
            $uibModal.open({
                templateUrl: 'userPaperDetail.html',
                controller: 'userPaperDetailCtrl',
                size: 'lg',
                resolve: {
                    selectedRow: $scope.selectedRow,
                },
            });
        }
        function queryExamedPapers() {
            Service.userpapers.query({status: 4}, function (data) {
                if (data.result == undefined) {
                    $scope.papers = data;
                    $scope.gridOptions.api.setRowData($scope.papers);
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
    .controller("userPaperDetailCtrl", function ($scope, toaster, $uibModalInstance, Service, selectedRow) {
        $scope.paper = selectedRow;
        $scope.paperDetail = {
            content: '',
            rightAnswer: '',
        };
        Service.userpaper.get({id: $scope.paper.userPaperId}, function (data) {
            if (data.result == undefined) {
                $scope.paperDetail.content = data.content;
                $scope.paperDetail.rightAnswer = data.rightAnswer;
            } else {
                toaster.pop('error', data.message);
            }
        })
        $scope.ok = function () {
            $scope.cancel();
        }
        $scope.cancel = function () {
            $uibModalInstance.dismiss();
        }
    })