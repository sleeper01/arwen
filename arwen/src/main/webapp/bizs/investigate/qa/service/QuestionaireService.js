define(['/arwen/bizs/common/service/http.js'],function(){
	app.factory('QuestionaireService',['HttpService',function(httpService){
		return {
			getQuestionaireList : function(params,page,pageSize){
				return httpService.post('/arwen/questionaire/getPageList.qh?page='+page+'&pageSize='+pageSize,params);
			}
		};
	}]);
});