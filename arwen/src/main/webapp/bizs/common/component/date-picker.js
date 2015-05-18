define(['/arwen/resources/metronic/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js'],function(){
	app.directive('datePicker',['$ocLazyLoad',function($ocLazyLoad){
		$ocLazyLoad.load([{
	        name:'com.qh.mainApp',
	        files : ['/arwen/resources/metronic/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css']
		}]);
		return{
			restrict : 'C',
			link : function(scope,ele,attrs){
				ele.datepicker({
	                rtl: Metronic.isRTL(),
	                orientation: "left",
	                format:"yyyy-mm-dd",
	                autoclose: true
	            });
			}
		};
	}]);
});