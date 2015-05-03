define(['/arwen/resources/metronic/assets/global/plugins/bootstrap-confirmation/bootstrap-confirmation.js'],function(){
	app.directive('comfirmationComponent',function(){
		return {
			restrict : 'A',
			scope : {
				okCall : '&',
//				cancelCall : '&'
			},
			link : function(scope,ele,attrs){
				ele.confirmation({
					container: 'body', 
					btnOkClass: 'btn-xs btn-success', 
					btnCancelClass: 'btn-xs btn-danger',
					onConfirm : scope.okCall,
//					onCancel : scope.cancelCall();
				});
			}
		};
	});
});