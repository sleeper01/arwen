define(['/arwen/resources/metronic/assets/global/plugins/bootstrap-growl/jquery.bootstrap-growl.min.js'],function(){
	app.factory('BubbleMessage',function(){
		return{
			show : function(opt){
				$.bootstrapGrowl(opt.msg, {
                    ele: 'body', // which element to append to
                    type: opt.type || 'info', // (null, 'info', 'danger', 'success', 'warning')
                    offset: {
                        from: opt.from || 'top',
                        amount: opt.from || 30
                    }, // 'top', or 'bottom'
                    align: opt.align || 'center', // ('left', 'right', or 'center')
                    width: opt.align || 'auto', // (integer, or 'auto')
                    delay: opt.delay || 2000, // Time while the message will be displayed. It's not equivalent to the *demo* timeOut!
                    allow_dismiss: opt.allow_dismiss || true, // If true then will display a cross to close the popup.
                    stackup_spacing: 10 // spacing between consecutively stacked growls.
                });
			}
		};
	});
});