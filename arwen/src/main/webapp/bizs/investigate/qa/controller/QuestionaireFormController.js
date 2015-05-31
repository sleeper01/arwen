define(['../service/QuestionaireService.js'],function(){
	app.controller('QuestionaireFormController',['$scope','QuestionaireService','$state',function($scope,service,$state){
		$scope.qa = {
			title : '',
			subtitle : '',
			desp : '',
			nameless : 'NO',
			expireDate : null
		};
		$scope.save = function(){
			console.log($scope.qa);
		};
		
		$scope.goToList = function(){
			$state.go('qa-list');
		};
	}]);
});