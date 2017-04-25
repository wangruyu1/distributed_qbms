angular.module("QBMS.filter", [])

    .filter("myDate", function () {
        return function (timestamp) {
            var date = new Date(timestamp);
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            var day = date.getDate();
            var hour = date.getHours();
            var minute = date.getMinutes();
            var second = "0" + date.getSeconds();
            if (month < 10) {
                month = "0" + month;
            }
            if (day < 10) {
                day = "0" + day;
            }
            if (hour < 10) {
                hour = "0" + hour;
            }
            if (minute < 10) {
                minute = "0" + minute;
            }
            return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
        }
    })
    .filter("leftTime", function () {
        return function (paper) {
            var useredTime = parseInt((new Date().getTime() - paper.startTime) / 60000);
            return paper.totalTime - useredTime;
        }
    })
