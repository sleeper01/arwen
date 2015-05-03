define(['/arwen/bizs/common/service/http.js'],function(){
	app.factory('AppTypeService',['HttpService',function(httpService){
		return {
			getAppTypeList : function(){
				return httpService.post('/arwen/apptype/list.qh',{},true);
			},
			saveAppType : function(data){
				return httpService.post('/arwen/apptype/persist.qh',data,true);
			},
			editApp : function(data){
				return httpService.post('/arwen/app/persist.qh',data,true);
			},
			addApp : function(data){
				return httpService.post('/arwen/apptype/addApp.qh',data,true);
			}
		};
	}]);
});