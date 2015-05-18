define(['/arwen/bizs/common/service/http.js'],function(){
	app.factory('QuestionLabelService',['HttpService',function(httpService){
		return {
			save : function(data){
				return httpService.post('/arwen/ql/persist.qh',data);
			},
			getList : function(){
				return httpService.post('/arwen/ql/getList.qh',{});
			}
		};
	}]);
});