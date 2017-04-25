angular.module("QBMS.services", ['ngResource'])

    .config(['$resourceProvider', '$httpProvider', function ($resourceProvider, $httpProvider) {
        // Don't strip trailing slashes from calculated URLs
        $resourceProvider.defaults.stripTrailingSlashes = false;
        $httpProvider.interceptors.push("httpInterceptor");
    }])

    .factory("httpInterceptor", ["$q", "$rootScope", 'toaster', '$window', function ($q, $rootScope, toaster, $window) {
        return {
            request: function (config) {
                return config || $q.when(config);
            },
            requestError: function (rejection) {
                toaster.pop('error', '请求错误');
                return $q.reject(rejection)
            },
            response: function (response) {
                if (typeof(response.data) == 'string' && response.data.indexOf('QBMS.login') != -1) {
                    $window.top.location.href = '/login.html';
                    // return null;
                } else if (response.data.result == false) {
                    toaster.pop('error', response.data.message);
                    return null;
                }
                return response || $q.when(response);
            },
            responseError: function (rejection) {
                if (typeof(rejection.data) == 'string' && rejection.data.indexOf('QBMS.login') != -1) {
                    $window.top.location.href = '/login.html';
                    // return null;
                }
                else {
                    toaster.pop("error", rejection.data.message);
                }
                return $q.reject(rejection);
            }
        };
    }])

    .factory("Service", function ($resource) {
        var pc = "/pc";
        var ui = "/ui";
        var manager = "/manager";
        var user = "/user"
        var service = {
            user: $resource(pc + "/user", {}, {}),
            username: $resource(pc + "/user/:id/username", {}, {}),
            checkpassword: $resource(pc + "/user/password", {}, {}),
            logout: $resource("/logout", {}, {}),
            modifyPassword: $resource(pc + "/user", {}, {put: {method: 'PUT'}}),

            //试卷分类
            paperCategory: $resource(manager + "/paper/category/:id", {}, {modify: {method: "PUT", isArray: false}}),
            paperCategories: $resource(manager + "/paper/categories", {}, {}),

            //试卷难度
            subjectDifficulty: $resource(manager + "/paper/subjectdifficulty/:id", {}, {modify: {method: "PUT"}}),
            subjectDifficulties: $resource(manager + "/paper/subjectdifficulties", {}, {}),
            //试题分类
            subjectCategory: $resource(manager + "/subject/category/:id", {}, {modify: {method: "PUT"}}),
            subjectCategories: $resource(manager + "/subject/categories", {}, {}),
            //试题
            subject: $resource(manager + "/subject/:id", {}, {modify: {method: "PUT"}}),
            subjectBasic: $resource(manager + "/subject/:id/basic", {}, {modify: {method: "PUT"}}),
            subjectContent: $resource(manager + "/subject/:id/content", {}, {modify: {method: "PUT"}}),
            subjectAnswer: $resource(manager + "/subject/:id/answer", {}, {modify: {method: "PUT"}}),
            subjects: $resource(manager + "/subjects", {}, {}),
            subjectsByUser: $resource(manager + "/subjects/user=:userId", {}, {}),
            subjectsByDifficulty: $resource(manager + "/subjects/difficulty=:dificulltyId", {}, {}),
            subjectsByCategory: $resource(manager + "/subjects/category=:categoryId", {}, {}),

            //paper
            paper: $resource(manager + "/paper/:id", {}, {modify: {method: 'PUT'}}),
            paper_baseinfo: $resource(manager + "/paper/:id/baseinfo", {}, {modify: {method: 'PUT'}}),
            papers: $resource(manager + "/papers", {}, {}),
            papersByUser: $resource(manager + "/papers/user=:userId", {}, {}),
            papersByDifficulty: $resource(manager + "/papers/difficulty=:difficultyId", {}, {}),
            addContent: $resource(manager + "/paper/:id/subject", {}, {}),
            addPaperAutomatic: $resource(manager + "/paper/:id", {}, {}),
            makePaper: $resource(manager + "/paper/:id/makepaper", {}, {}),

            //userpaper
            userpapers: $resource(user + "/userpapers/status=:status", {}, {}),
            commitedUserPapers: $resource(manager + "/userpapers/commited", {}, {}),
            commitUserPaper: $resource(user + "/userPaper/:id", {}, {modify: {method: 'PUT'}})
        };
        return service;
    })

    //同步请求
    .factory('syncService', ['$http', '$q', function ($http, $q) {
        return {
            synSubjectCategories: function () {
                var deferred = $q.defer(); // 声明延后执行，表示要去监控后面的执行
                $http(
                    {
                        method: 'GET',
                        url: '/subject/categories',
                    })
                    .then(function (data, status, headers, config) {
                        deferred.resolve(data);  // 声明执行成功，即http请求数据成功，可以返回数据了
                    }, (function (data, status, headers, config) {
                        deferred.reject(data);   // 声明执行失败，即服务器返回错误
                    }));
                return deferred.promise;   // 返回承诺，这里并不是最终数据，而是访问最终数据的API
            },
            syncSubjectDifficulties: function () {
                var deferred = $q.defer(); // 声明延后执行，表示要去监控后面的执行
                $http({
                    method: 'GET',
                    url: '/paper/subjectdifficulties',
                }).then(function (data, status, headers, config) {
                    deferred.resolve(data);  // 声明执行成功，即http请求数据成功，可以返回数据了
                }).catch(function (data, status, headers, config) {
                    deferred.reject(data);   // 声明执行失败，即服务器返回错误
                });
                return deferred.promise;
            },
        }
    }]);
