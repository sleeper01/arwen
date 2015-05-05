define(['../service/RoleService.js',
        '../../common/service/bubble-message.js',
        '../../common/filter/status-filter.js',
        '../../common/component/selector-component.js',
        '../../common/component/comfirmation-component.js'],function(){
	app.controller('RoleController',['$scope','RoleService','BubbleMessage','$state',function($scope,service,bubbleMessage,$state){
		$scope.list = [],
		$scope.role = {},
		$scope.accounts = [],
		$scope.title='',
		$scope.showAccounts = false;
		$scope.accountId = '';
		
		$scope.load = function(){
			service.getList().then(function(data){
				if(data){
					$scope.list = data;
				}
			});
		};
		
		$scope.load();
		
		$scope.addRole = function(){
			$scope.role = {
				name : '',
				status : 'ENABLE'
			};
			$('#role-form').modal('show');
		};
		
		$scope.editRole = function(index){
			$scope.role = $scope.list[index];
			$scope.showAccounts = false;
			$('#role-form').modal('show');
		};
		
		$scope.save = function(){
			if($scope.role){
				service.save($scope.role).then(function(data){
					if(data && data.success){
						$('#role-form').modal('hide');
						$scope.load();
						bubbleMessage.show({msg:'操作成功!'});
					}
				});
			}
		};
		
		$scope.listAccount = function(index){
			$scope.showAccounts = true;
			$scope.role = $scope.list[index];
			$scope.title = $scope.role.name;
			$scope.accounts = $scope.role.accounts;
		};
		
		$scope.addAccount = function(){
			$("#role-account-add").modal('show');
		};
		
		$scope.config = {
			url:'/arwen/account/getAccountByNameLike.qh',
			field : {}
		};
		
		$scope.addRoleAccount = function(){
			var account = $scope.config.field.select2('data');
			if(account){
				$scope.config.field.select2('data',null);
				for(var i=0;i<$scope.role.accounts.length;i++){
					if($scope.role.accounts[i].id == account.id){
						bubbleMessage.show({
							msg : '账号已属于该角色.',
							type : 'warning'
						});
						return false;
					}
				}
			}
			service.addAccount({
				id : $scope.role.id,
				accountId : account.id
			}).then(function(data){
				if(data && data.success){
					$scope.load();
					$scope.showAccounts = false;
					bubbleMessage.show({
						msg : '操作成功!',
						type : 'success'
					});
				}else{
					bubbleMessage.show({
						msg : data.msg,
						type : 'danger'
					});
				}
			});
		};
		
		$scope.setAccountId = function(accountId){
			$scope.accountId = accountId;
		};
		
		$scope.removeAccount = function(){
			if($scope.accountId){
				service.removeAccount({
					id : $scope.role.id,
					accountId : $scope.accountId
				}).then(function(data){
					if(data && data.success){
						$scope.load();
						$scope.showAccounts = false;
						bubbleMessage.show({
							msg : '操作成功!',
							type : 'success'
						});
					}else{
						bubbleMessage.show({
							msg : data.msg,
							type : 'danger'
						});
					}
				});
			}
		};
		
		$scope.editRoleAuth = function(id){
			$state.go('role-auth',{
				id : id
			});
		};
	}]);
});