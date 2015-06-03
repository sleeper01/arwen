define(['../service/QuestionaireService.js'],function(){
	app.controller('QuestionaireController',['$scope','QuestionaireService','$state',function($scope,service,$state){
		$scope.list=[],$scope.params={};
		$scope.page={
			busy:false,
			start : 1,
			pageSize : 20,
			nextPage : function(){
				if(this.busy)
					return;
				this.busy = true;
				service.getQuestionaireList($scope.params,this.start,this.pageSize).then(function(data){
					if(data && data.length > 0){
						angular.forEach(data,function(item){
							$scope.list.push(item);
						});
						$scope.page.start ++;
					}
					$scope.page.busy = false;
				});
			},
			reload : function(){
				this.start = 1;
				this.nextPage();
			}
		};
		
		$scope.addQA = function(){
			$state.go('qa-form');
		};
		$scope.editQA = function(id){
			$state.go('qa-form',{
				id : id
			});
		};
	}]);
});