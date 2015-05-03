define(['../service/AccountService.js',
        '../../common/service/bubble-message.js',
        '../../common/filter/status-filter.js',
        '../../common/component/comfirmation-component.js'],function(){
	app.controller('AccountListController',['$scope','AccountService','BubbleMessage',function($scope,service,bubbleMessage){
		$scope.list = [],$scope.account = {};
		$scope.load = function(){
			service.getList().then(function(data){
				$scope.list = data;
			});
		};
		$scope.load();
		
		$scope.addAccount = function(){
			$scope.account = {
				name : '',
				status : 'ENABLE'
			};
			$("#account-form").modal('show');
		};
		
		$scope.editAccount = function(index){
			$scope.account = $scope.list[index];
			$("#account-form").modal('show');
		};
		
		$scope.cancel = function(){
			$scope.load();
			$("#account-form").modal('hide');
		};
		
		$scope.save = function(){
			service.save($scope.account).then(function(data){
				bubbleMessage.show({msg:'操作成功!'});
				$scope.load();
			});
		};
	}]);
});