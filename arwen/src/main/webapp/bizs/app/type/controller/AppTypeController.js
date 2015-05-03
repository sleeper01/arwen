define(['../service/AppTypeService.js',
        '../../../common/service/bubble-message.js',
        '../../../common/filter/status-filter.js',
        '../../../common/component/comfirmation-component.js'],
	function() {
		app.controller('AppTypeController', [ '$scope','AppTypeService','BubbleMessage', function($scope,service,bubbleMessage) {
			$scope.appTypes = [],
			$scope.apps = [],
			$scope.title = "",
			$scope.appType = {},
			$scope.app = {},
			$scope.edit = false;
			$scope.showAppList = false;
			$scope.load = function(){
				service.getAppTypeList().then(function(data){
					$scope.appTypes = data;
				});
			};
			
			$scope.load();
			
			$scope.editAppType = function(index){
				$scope.appType = $scope.appTypes[index];
				$scope.edit = true;
				$('#app-type-form').modal('show');
			};
			
			$scope.addAppType = function(){
				$scope.appType = {
					name : '',
					status : 'ENABLE',
					sort : 0,
					iconClass : ''
				};
				$scope.edit = false;
				$('#app-type-form').modal('show');
			};
			
			$scope.listApp = function(index){
				$scope.title = $scope.appTypes[index].name;
				$scope.apps = $scope.appTypes[index].apps;
				$scope.appType = $scope.appTypes[index];
				$scope.showAppList = true;
			};
			
			$scope.editApp = function(index){
				$scope.app = $scope.apps[index];
				$('#app-form').modal('show');
			};
			
			$scope.addApp = function(){
				$scope.app = {
					name : '',
					stateName : '',
					templateUrl : '',
					ctlName : '',
					ctlUrl : '',
					sort : '',
					iconClass : '',
					status : 'ENABLE',
					showInMenus : 'YES'
				};
				$('#app-form').modal('show');
			};
			
			$scope.closeModal = function(id){
				$('#'+id).modal('hide');
				$scope.load();
			};
			
			$scope.saveAppType = function(){
				service.saveAppType($scope.appType).then(function(data){
					if(data && data.success){
						bubbleMessage.show({msg:'操作成功!'});
						$('#app-type-form').modal('hide');
						$scope.load();
					}
				});
			};
			
			$scope.saveApp = function(){
				if($scope.app.id){
					service.editApp($scope.app).then(function(data){
						if(data && data.success){
							bubbleMessage.show({msg:'操作成功!'});
							$('#app-type').modal('hide');
							$scope.load();
						}
					});
				}else{
					$scope.app.appTypeId = $scope.appType.id;
					service.addApp($scope.app).then(function(data){
						if(data && data.success){
							bubbleMessage.show({msg:'操作成功!'});
							$('#app-type').modal('hide');
							$scope.load();
						}
					});
				}
			};
		}]);
	}
);