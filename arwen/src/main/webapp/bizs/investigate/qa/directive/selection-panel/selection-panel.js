define(function(){
	app.directive('selectionPanel',function(){
		return{
			restrict : 'A',
			scope : {
				question : '=',
			},
			templateUrl : '/arwen/bizs/investigate/qa/directive/selection-panel/selection-panel.html',
			replace : true
		};
	});
});
