angular.module("QBMS.direcetive", [])

    .directive('draggable', ['$document', function ($document) {
        return function (scope, element, attr) {
            var startX = 0, startY = 0, x = 0, y = 0;
            element = angular.element(document.getElementsByClassName("modal-dialog"));
            element.css({
                position: 'relative',
                cursor: 'move'
            });

            element.on('mousedown', function (event) {
                // Prevent default dragging of selected content
                event.preventDefault();
                startX = event.pageX - x;
                startY = event.pageY - y;
                $document.on('mousemove', mousemove);
                $document.on('mouseup', mouseup);
            });

            function mousemove(event) {
                y = event.pageY - startY;
                x = event.pageX - startX;
                element.css({
                    top: y + 'px',
                    left: x + 'px'
                });
            }

            function mouseup() {
                $document.off('mousemove', mousemove);
                $document.off('mouseup', mouseup);
            }
        };
    }])
    // .directive("datetimepicker", function () {
    //     return {
    //         restrict: "EA",   //指令作用范围是element或attribute
    //         require: "ngModel",  //控制器是指令标签对应的ngModel
    //         link: function (scope, element, attrs, ctrl) {
    //
    //             var unregister = scope.$watch(function () {               //关键点，下面详述
    //
    //                 $(element).append("<input id='date-" + attrs.dateid + "' style='border:none;width:100%' value='" + ctrl.$modelValue + "'>"); //template用不好，于是用这个笨办法加上input标签
    //
    //                 element.on('change', function () {  //注册onChange事件，设置viewValue
    //                     scope.$apply(function () {
    //                         ctrl.$setViewValue($("#date-" + attrs.dateid).val());
    //                     });
    //                 });
    //
    //                 element.on('click', function () {    //click触发日期框
    //                     var date = $("#date-timepicker");
    //                     var x = attrs.dateid;
    //                     $("#date-" + attrs.dateid).datetimepicker({
    //                         format: attrs.format || 'Y/m/d h:i',   //格式
    //                         onClose: function () {                   //关闭日期框时手动触发change事件
    //                             element.change();
    //                         }
    //                     });
    //                 });
    //
    //                 element.click();        //第一次绑定事件，模拟一次click，否则肯能要点两下才会出日期框
    //
    //                 return ctrl.$modelValue;
    //             }, initialize);
    //
    //             function initialize(value) {  //下面再说
    //                 ctrl.$setViewValue(value);
    //                 unregister();
    //             }
    //         }
    //     }
    // });


;