define(['../service/QuestionaireTypeService.js',
        '/arwen/bizs/common/filter/status-filter.js',
        '../../../common/service/bubble-message.js'],function(){
	app.controller('QuestionaireTypeController',['$scope','QuestionaireTypeService','BubbleMessage',function($scope,service,bubbleMessage){
		$scope.list=[],$scope.type={};
		
		$scope.load = function(){
			service.getList().then(function(data){
				$scope.list = data;
			});
		};
		
		$scope.load();
		
		$scope.addType = function(){
			$scope.type = {
				name : '',
				status : 'ENABLE'
			};
			$('#questionaire-type-form').modal('show');
		};
		
		$scope.editType = function(index){
			$scope.type = $scope.list[index];
			$('#questionaire-type-form').modal('show');
		};
		
		$scope.save = function(){
			service.save($scope.type).then(function(data){
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
			$scope.type = {
				name : '',
				status : 'ENABLE'
			};
			$('#questionaire-type-form').modal('hide');
		};
	}]);
});