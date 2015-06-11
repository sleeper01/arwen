define(['../service/QuestionaireService.js',
        '/arwen/bizs/investigate/question/filter/question-type.js',
        '/arwen/bizs/common/component/comfirmation-component.js',
        '/arwen/bizs/common/service/bubble-message.js'],function(){
	app.controller('QuestionAddController',['$scope','QuestionaireService','$state','BubbleMessage',function($scope,service,$state,bubbleMessage){
		$scope.step = "question-search",$scope.params={},$scope.list=[];
		
		$scope.goTo = function(st){
			if(st == 'form'){
				$scope.ctl.showQuesionPanel = false;
				$scope.step = "question-search";
				$scope.params={};
			}else if(st == 'search'){
				$scope.step = "question-search";
			}
		};
		$scope.clearSearch = function(){
			$scope.params={};
		};
		
		$scope.search = function(){
			service.getQuestionList($scope.params,1,100).then(function(data){
				if(data && data.length > 0){
					$scope.list = data;
					$scope.step = 'question-list';
				}
			});
		};
		
		$scope.selectQuestion = function(index){
			service.addQuestion({
				topicId : $scope.curClick.id,
				questionId : $scope.list[index].id
			}).then(function(data){
				if(data && data.success){
					bubbleMessage.show({msg:'操作成功!'});
					$scope.params={}
					$scope.load();
					$scope.ctl.showQuesionPanel = false;
				}
			});
		};
	}]);
});
