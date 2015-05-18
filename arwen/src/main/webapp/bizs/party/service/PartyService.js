define(['/arwen/bizs/common/service/http.js'],function(){
	app.factory('PartyService',['HttpService',function(httpService){
		return {
			saveParty : function(data){
				return httpService.post('/arwen/party/persist.qh',data);
			},
			addDept : function(data){
				return httpService.post('/arwen/party/addDept.qh',data);
			},
			addChild : function(data){
				return httpService.post('/arwen/dept/addChild.qh',data);
			},
			saveDept : function(data){
				return httpService.post('/arwen/dept/persist.qh',data);
			},
			getPartyById : function(id){
				return httpService.post('/arwen/party/getPartyById.qh?id='+id,{});
			},
			getDeptById : function(id){
				return httpService.post('/arwen/dept/getDeptById.qh?id='+id,{});
			},
			addUser : function(data){
				return httpService.post('/arwen/dept/addUser.qh',data);
			},
			saveUser : function(data){
				return httpService.post('/arwen/user/persist.qh',data);
			}
		};
	}]);
});