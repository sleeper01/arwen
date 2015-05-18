define(['../service/QuestionListService.js',
        '../filter/question-type.js',
        '/arwen/bizs/common/component/date-picker.js'],function(){
	app.controller('QuestionListController',['$scope','QuestionListService','$state',function($scope,service,$state){
		$scope.list = [],$scope.params={};
		$scope.page={
			busy:false,
			start : 1,
			pageSize : 20,
			nextPage : function(){
				if(this.busy)
					return;
				this.busy = true;
				service.getQuestionList($scope.params,this.start,this.pageSize).then(function(data){
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
		
		$scope.addQuestion = function(){
			$state.go('question-edit');
		};
		
		$scope.editQuestion = function(id){
			$state.go('question-edit',{
				id : id
			});
		};
		
		$scope.openSearch = function(){
			$("#question-search-panel").modal('show');
		};
		
		$scope.clearSearch = function(){
			$scope.params = {
				subject : '',
				type : '',
				score : null,
				firLevelLabel : '',
				secLevelLabel : '',
				updateTimeBegin : '',
				updateTimeEnd : ''
			};
		};
		
		$scope.clearSearch();
		
		$scope.search = function(){
			$scope.list = [];
			$scope.page.reload();
		};
	}]);
});