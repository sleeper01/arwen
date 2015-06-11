define(['/arwen/bizs/common/service/http.js'],function(){
	app.factory('QuestionaireService',['HttpService',function(httpService){
		return {
			getQuestionaireList : function(params,page,pageSize){
				return httpService.post('/arwen/questionaire/getPageList.qh?page='+page+'&pageSize='+pageSize,params);
			},
			save : function(data){
				return httpService.post('/arwen/questionaire/persist.qh',data,true);
			},
			get : function(id){
				return httpService.get('/arwen/questionaire/get.qh?id='+id);
			},
			addTopic : function(data){
				return httpService.post('/arwen/questionaire/addTopic.qh',data,true);
			},
			updateTopic : function(data){
				return httpService.post('/arwen/topic/persist.qh',data,true);
			},
			getTopicById : function(id){
				return httpService.get('/arwen/topic/get.qh?id='+id);
			},
			getQuestionList : function(params,page,pageSize){
				return httpService.post('/arwen/question/getQuestionList.qh?page='+page+'&pageSize='+pageSize,params);
			},
			addQuestion : function(data){
				return httpService.post('/arwen/topic/addQuestion.qh',data,true);
			}
		};
	}]);
});
