angular.module("QBMS.router", ['ui.router'])

    .config(function ($stateProvider, $urlRouterProvider) {
        //
        // For any unmatched url, redirect to /state1
        // $urlRouterProvider.otherwise("/view1");
        //
        // Now set up the states
        $stateProvider
            .state('demo', {
                url: "/demo",
                templateUrl: "pages/demo/demo.html",
                controller: "DemoController",
            })
            .state('overview', {
                url: "/overview",
                templateUrl: "pages/overview/overview.html",
                controller: "OverviewController",
            })
            .state('category', {
                url: "/category",
                templateUrl: "pages/category/papercategory.html",
                controller: "CategoryController",
            })
            .state('subjectDifficulty', {
                url: "/subjectDifficulty",
                templateUrl: "pages/subjectDifficulty/subjectDifficulty.html",
                controller: "SubjectDifficultyController",
            })
            .state('subjectCategory', {
                url: "/subjectCategory",
                templateUrl: "pages/category/subjectCategory.html",
                controller: "SubjectCategoryController",
            })
            .state('subject', {
                url: "/subject",
                templateUrl: "pages/subject/subject.html",
                controller: "SubjectController",
            })
            .state('paper', {
                    url: "/paper",
                    templateUrl: "pages/paper/paper.html",
                    controller: "PaperController",
                }
            )
            .state('paper/manuals', {
                    url: "/paper/manuals",
                    templateUrl: "pages/paper/addPaperManual.html",
                    controller: "AddPaperManualController",
                }
            )
            .state('paper/automation', {
                    url: "/paper/automation",
                    templateUrl: "pages/paper/addPaperAutomatic.html",
                    controller: "AddPaperAutomaticController",
                }
            )
    });
