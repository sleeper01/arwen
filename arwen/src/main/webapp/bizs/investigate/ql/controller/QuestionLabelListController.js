define(['../service/QuestionLabelService.js',
        '/arwen/bizs/common/filter/status-filter.js',
        '../filter/level-filter.js',
        '../../../common/service/bubble-message.js'],function(){
	app.controller('QuestionLabelListController',['$scope','QuestionLabelService','BubbleMessage',function($scope,service,bubbleMessage){
		$scope.list=[],$scope.model={};
		
		$scope.load = function(){
			service.getList().then(function(data){
				$scope.list = data;
			});
		};
		
		$scope.load();
		
		$scope.addLabel = function(){
			$scope.model = {
				name : '',
				level : 'FIRST_LEVEL',
				status : 'ENABLE'
			};
			$('#question-label-form').modal('show');
		};
		
		$scope.editLabel = function(index){
			$scope.model = $scope.list[index];
			$('#question-label-form').modal('show');
		};
		
		$scope.save = function(){
			service.save($scope.model).then(function(data){
				if(data && data.success){
					bubbleMessage.show({msg:'操作成功!'});
					$scope.load();
				}else{
					bubbleMessage.show({
						msg : data.msg,
						type : 'danger'
					})
				}
			});
		};
		
		$scope.cancel = function(){
			$scope.model = {
				name : '',
				level : 'FIRST_LEVEL',
				status : 'ENABLE'
			};
			$('#question-label-form').modal('hide');
		};
	}]);
});