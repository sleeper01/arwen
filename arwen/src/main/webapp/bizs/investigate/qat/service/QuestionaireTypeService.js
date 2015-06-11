define(['../../../common/service/http.js'],function(){
	app.factory('QuestionaireTypeService',['HttpService',function(httpService){
		return {
			save : function(data){
				return httpService.post('/arwen/questionaireType/persist.qh',data);
			},
			getList : function(){
				return httpService.post('/arwen/questionaireType/getList.qh',{});
			}
		};
	}]);
});