define(['../service/RoleService.js',
        '../../common/service/bubble-message.js'],function(){
	app.controller('RoleAuthController',['$scope','RoleService','$stateParams','$timeout','$state','BubbleMessage',function($scope,service,$stateParams,$timeout,$state,bubbleMessage){
		$scope.role = {},$scope.appTypes = [];
		service.getRoleById($stateParams.id).then(function(data){
			$scope.role = data;
		});
		service.getAppTypes().then(function(data){
			$scope.appTypes = data;
		});
		
		$timeout(function(){
			angular.forEach($scope.role.apps,function(app){
				$('#'+app.id).attr("checked", true);
			});
		},1500);
		
		$scope.cancel = function(){
			$state.go("role-list");
		};
		
		$scope.save = function(){
			var checkboxes = $('input[type="checkbox"]'),_apps=[];
			angular.forEach(checkboxes,function(c){
				if(c.checked){
					_apps.push({id:c.id});
				}
			});
			service.addApps({
				id : $scope.role.id,
				apps : _apps
			}).then(function(data){
				if(data && data.success){
					bubbleMessage.show({
						msg : '操作成功!',
						type : 'success'
					});
					$scope.cancel();
				}else{
					bubbleMessage.show({
						msg : data.msg,
						type : 'danger'
					});
				}
			});
		};
	}]);
});