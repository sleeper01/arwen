define(['../service/QuestionEditService.js',
        '../../../common/service/bubble-message.js'],function(){
	app.controller('QuestionEditController',['$scope','QuestionEditService','$state','$stateParams','BubbleMessage','$timeout',function($scope,service,$state,$stateParams,bubbleMessage,$timeout){
		$scope.model = {},
		$scope.showOption = false,
		$scope.option={},
		$scope.firLevelLabelList=[],
		$scope.secLevelLabelList=[];
		service.getQuestionLabelList({level:'FIRST_LEVEL'}).then(function(data){
			if(data){
				$scope.firLevelLabelList = data;
			}
		});
		
		service.getQuestionLabelList({level:'SECOND_LEVEL'}).then(function(data){
			if(data){
				$scope.secLevelLabelList = data;
			}
		});
		$scope.loadById = function(){
			service.getQuestionById($stateParams.id||$scope.model.id).then(function(data){
				if(data){
					$scope.model = data;
				}
			});
		};
		if($stateParams.id){
			$timeout(function(){
				$scope.loadById();
			},500);
		}else{
			$scope.model = {
				type : 'SINGLE_SELECTION',
				subject : '',
				score : null,
				status : 'ENABLE',
				firLevelLabel : '',
				secLevelLabel : '',
				options : []
			}
		}
		
		$scope.gotoList = function(){
			$state.go('question-list');
		};
		
		$scope.save = function(){
			service.save($scope.model).then(function(res){
				if(res && res.success){
					$scope.on_edit = true;
					bubbleMessage.show({msg:'操作成功!'});
					$scope.model = res.data;
				}else{
					bubbleMessage.show({
						msg : data.msg,
						type : 'danger'
					});
				}
			});
		};
		
		$scope.$watch('model.id',function(v){
			if(v){
				if($scope.model.type != 'SUBJECTIVITY'){
					$scope.showOption = true;
				}else{
					$scope.showOption = false;
				}
			}
		});
		
		$scope.addOption = function(){
			$scope.option = {
				remarkable : 'NO',
				num : '',
				content : '',
				status : 'ENABLE'
			};
			$('#option-form').modal('show');
		};
		
		$scope.editOption = function(index){
			$scope.option = $scope.model.options[index];
			$('#option-form').modal('show');
		};
		
		$scope.saveOption = function(){
			if($scope.option.id){
				service.updateOption($scope.option).then(function(data){
					if(data && data.success){
						bubbleMessage.show({msg:'操作成功!'});
						$scope.loadById();
					}else{
						bubbleMessage.show({
							msg : data.msg,
							type : 'danger'
						});
					}
				});
			}else{
				$scope.option.questionId = $scope.model.id;
				service.addOption($scope.option).then(function(data){
					if(data && data.success){
						bubbleMessage.show({msg:'操作成功!'});
						$scope.loadById();
					}else{
						bubbleMessage.show({
							msg : data.msg,
							type : 'danger'
						});
					}
				});
			}
		};
		
	}]);
});