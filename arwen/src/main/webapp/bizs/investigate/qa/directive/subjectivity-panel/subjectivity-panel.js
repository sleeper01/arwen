define(function(){
	app.directive('subjectivityPanel',function(){
		return{
			restrict : 'A',
			scope : {
				question : '=',
			},
			templateUrl : '/arwen/bizs/investigate/qa/directive/subjectivity-panel/subjectivity-panel.html',
			replace : true
		};
	});
});
