define(['../service/QuestionaireService.js',
        '/arwen/bizs/common/component/date-time-picker.js',
        '/arwen/bizs/common/service/bubble-message.js',
        '/arwen/bizs/common/component/mouse-hover.js'],function(){
	app.controller('QuestionaireFormController',['$scope','QuestionaireService','$state','$stateParams','BubbleMessage','$ocLazyLoad',function($scope,service,$state,$stateParams,bubbleMessage,$ocLazyLoad){
		$ocLazyLoad.load([{
	        name:'com.qh.mainApp',
	        files : ['/arwen/bizs/investigate/resources/qa.css']
		}]);
		$scope.editBaseInfo = true;
		if($stateParams.id){
			service.get($stateParams.id).then(function(data){
				$scope.qa = data;
				$scope.editBaseInfo = false;
			});
		}else{
			$scope.qa = {
				title : '',
				subtitle : '',
				desp : '',
				nameless : 'NO',
				expireDate : null
			};
		}
		$scope.save = function(){
			if(!$scope.qa.id){
				service.save($scope.qa).then(function(res){
					if(res && res.success){
						bubbleMessage.show({msg:'操作成功!'});
						$state.go('qa-form',{
							id : res.data.id
						});
					}
				});
			}else{
				service.save($scope.qa).then(function(res){
					if(res && res.success){
						$scope.editBaseInfo = false;
						bubbleMessage.show({msg:'操作成功!'});
					}
				});
			}
		};
		
		$scope.goToList = function(){
			$state.go('qa-list');
		};
	}]);
});