angular.module("QBMS.filter", [])

    .filter("myDate", function () {
        return function (timestamp) {
            var date = new Date(timestamp);
            return date.getFullYear() + "-" + date.getMonth() + "-" + date.getDay();
        }
    })
