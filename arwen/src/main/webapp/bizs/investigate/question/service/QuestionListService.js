define(['/arwen/bizs/common/service/http.js'],function(){
	app.factory('QuestionListService',['HttpService',function(httpService){
		return {
			getQuestionList : function(params,page,pageSize){
				return httpService.post('/arwen/question/getQuestionList.qh?page='+page+'&pageSize='+pageSize,params);
			}
		};
	}]);
});