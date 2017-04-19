angular.module('QBMS', ['toaster', 'ngAnimate',
    'QBMS.services', "QBMS.controllers", "QBMS.router",
    'agGrid', 'ui.bootstrap', 'QBMS.direcetive', 'QBMS.filter', 'QBMS.demo','ui.bootstrap.datetimepicker'])

    .config(['$locationProvider', function ($locationProvider) {
        $locationProvider.html5Mode(false);
        $locationProvider.hashPrefix('!');
    }])
    .run(['$rootScope', function ($rootScope) {
        $rootScope.lockScreen = false;
    }]);
;

