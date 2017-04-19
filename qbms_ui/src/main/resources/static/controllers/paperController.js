angular.module("QBMS.controllers")

    .controller("PaperController", function ($filter, $scope, Service, $uibModal, toaster) {
        $scope.papers = [];
        $scope.paper = {};
        $scope.selectedRow = undefined;
        $scope.paperDetail = {
            paperId: '',
            content: '',
            answer: '',
        };
        var columnDefs = [
            {headerName: "编号", field: "paperId", pinned: 'left'},
            {headerName: "名称", field: "name"},
            {headerName: "分类", field: "categoryName"},
            {headerName: "困难等级", field: "difficultyName"},
            {
                headerName: "创建时间", field: "createTime", cellRenderer: function (param) {
                return $filter('myDate')(param.value);
            }
            },
            {headerName: "创建者", field: "userName"},
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

        //查询试卷
        queryPapers();

        $scope.getPaperDetail = function () {
            $uibModal.open({
                templateUrl: 'paperDetail.html',
                controller: 'paperDetailCtrl',
                size: 'lg',
                resolve: {
                    selectedRow: $scope.selectedRow,
                },
            });
        }
        $scope.deletePaper = function () {
            $uibModal.open({
                templateUrl: 'deletePaper.html',
                controller: 'deletePaperCtrl',
                resolve: {
                    selectedRow: $scope.selectedRow,
                }
            });
        }
        //出题
        $scope.makePaper = function () {
            $uibModal.open({
                templateUrl: 'makePaper.html',
                controller: 'makePaperCtrl',
                resolve: {
                    selectedRow: $scope.selectedRow,
                }
            });
        }

        function onSelectionChanged() {
            $scope.selectedRow = $scope.gridOptions.api.getSelectedRows()[0];
            $scope.$apply();
        }

        function queryPapers() {
            Service.papers.query(function (data) {
                if (data.result == undefined) {
                    $scope.papers = data;
                    $scope.gridOptions.api.setRowData($scope.papers);
                } else {
                    toaster('error', data.message);
                }
            });
        }
    })
    .controller("paperDetailCtrl", function ($scope, toaster, $uibModalInstance, Service, selectedRow) {
        $scope.paper = selectedRow;
        $scope.paperDetail = {
            content: '',
            answer: '',
        };
        Service.paper.get({id: $scope.paper.paperId}, function (data) {
            if (data.result == undefined) {
                $scope.paperDetail.content = data.content;
                $scope.paperDetail.answer = data.answer;
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
    .controller('deletePaperCtrl', function ($scope, toaster, $uibModalInstance, selectedRow, Service, $state) {
        $scope.paper = selectedRow;
        $scope.cancel = function () {
            $uibModalInstance.dismiss();
        }
        $scope.ok = function () {
            Service.paper.delete({id: $scope.paper.paperId}, function (data) {
                if (data.result == true) {
                    $scope.cancel();
                    $state.reload();
                    toaster.pop('success', data.message);
                } else {
                    toaster.pop('error', data.message);
                }
            });
        }
    })
    .controller("makePaperCtrl", function ($scope, $uibModalInstance, toaster, Service, $document,selectedRow) {
        $scope.paper = {
            'startTime': '',
            'paperId': selectedRow.paperId,
            'totalTime': undefined,
        };
        $scope.cancel = function () {
            $uibModalInstance.dismiss();
        }
        $scope.ok = function () {
            Service.makePaper.save({id:$scope.paper.paperId}, $scope.paper, function (data) {
                if (data.result == true) {
                    toaster.pop('success', data.message);
                    $scope.cancel();
                } else {
                    toaster.pop('error', data.message);
                }
            })
        }
    })