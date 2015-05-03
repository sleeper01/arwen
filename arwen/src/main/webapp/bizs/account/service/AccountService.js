define(['/arwen/bizs/common/service/http.js'],function(){
	app.factory('AccountService',['HttpService',function(httpService){
		return {
			getList : function(){
				return httpService.post('/arwen/account/getList.qh',{});
			},
			save : function(data){
				return httpService.post('/arwen/account/persist.qh',data);
			}
		};
	}]);
});
