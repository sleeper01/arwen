define(['/arwen/resources/metronic/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js'],function(){
	app.directive('dateTimePicker',['$ocLazyLoad',function($ocLazyLoad){
		$ocLazyLoad.load([{
	        name:'com.qh.mainApp',
	        files : ['/arwen/resources/metronic/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css']
		}]);
		return{
			restrict : 'C',
			link : function(scope,ele,attrs){
				ele.datetimepicker({
					autoclose: true,
		            isRTL: Metronic.isRTL(),
		            format: "yyyy-mm-dd hh:ii:ss",
		            pickerPosition: (Metronic.isRTL() ? "bottom-right" : "bottom-left")
	            });
			}
		};
	}]);
});