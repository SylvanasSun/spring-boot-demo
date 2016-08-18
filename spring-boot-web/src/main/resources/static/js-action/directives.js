/**
 * Created by sylvanasp on 2016/7/28.
 */
// 定义一个指令名为datePicker
actionApp.directive('datePicker', function () {
    return {
        // 限制为属性指令和样式指令
        restrict:'AC',
        // 使用link函数来定义指令,在link函数中可使用当前scope、当前元素、当前属性.
        link:function(scope,elem,attrs){
            // 初始化jqueryui的datePicker(jquery的写法是$('#id').datePicker()).
            elem.datepicker();
        }
    };
});
