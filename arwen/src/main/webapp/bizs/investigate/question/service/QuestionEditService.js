define(['/arwen/bizs/common/service/http.js'],function(){
	app.factory('QuestionEditService',['HttpService',function(httpService){
		return {
			save : function(data){
				return httpService.post('/arwen/question/persist.qh',data,true);
			},
			getQuestionById : function(id){
				return httpService.get('/arwen/question/get.qh?id='+id,true);
			},
			getQuestionLabelList : function(data){
				return httpService.post('/arwen/ql/getList.qh',data);
			},
			addOption : function(data){
				return httpService.post('/arwen/question/addOption.qh',data);
			},
			updateOption : function(data){
				return httpService.post('/arwen/option/persist.qh',data);
			}
		};
	}]);
});