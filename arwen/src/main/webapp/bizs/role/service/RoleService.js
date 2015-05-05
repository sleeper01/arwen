define(['/arwen/bizs/common/service/http.js'],function(){
	app.factory('RoleService',['HttpService',function(httpService){
		return {
			getList : function(){
				return httpService.post('/arwen/role/getList.qh',{});
			},
			save : function(data){
				return httpService.post('/arwen/role/persist.qh',data);
			},
			addAccount : function(data){
				return httpService.post('/arwen/role/addAccount.qh',data);
			},
			addApps : function(data){
				return httpService.post('/arwen/role/addApps.qh',data);
			},
			removeAccount : function(data){
				return httpService.post('/arwen/role/removeAccount.qh',data);
			},
			getRoleById : function(id){
				return httpService.post('/arwen/role/getRoleById.qh?id='+id,{});
			},
			getAppTypes : function(){
				return httpService.post('/arwen/apptype/getEnableList.qh',{});
			}
		};
	}]);
});