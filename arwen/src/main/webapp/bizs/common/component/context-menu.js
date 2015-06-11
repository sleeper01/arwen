define(['/arwen/resources/metronic/assets/global/plugins/bootstrap-contextmenu/bootstrap-contextmenu.js'],function(){
	app.directive('contextMenu',function(){
		return{
			restrict : 'A',
			scope : {
				option : '=',
				beforeShow : '&'
			},
			link : function(scope,ele,attrs){
				ele.contextmenu(scope.option||{});
				 $(scope.option.target).on('show.bs.context', function (e) {
			            scope.beforeShow({e:e});
			     });
			}
		};
	});
});
