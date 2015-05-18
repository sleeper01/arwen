define(function(){
	app.factory('HttpService',['$http','$q',function($http,$q){
		return {
			post : function(url,data,block){
				if(block){
					Metronic.blockUI();
				}
				var d = $q.defer();
   	    		var promise = $http({
   			   		url : url,
   					method : "POST",
   					data : data,
   					headers : {
   						'Content-Type' : 'application/json;charset=UTF-8'
   					}
   				});
   	    		promise.success(function(data){
   					d.resolve(data);
   					if(block){
   						Metronic.unblockUI();
   					}
   				});
   	    		return d.promise;
			},
			get : function(url,block){
				if(block){
					Metronic.blockUI();
				}
				var d = $q.defer();
   	    		var promise = $http({
   			   		url : url,
   					method : "get",
   					headers : {
   						'Content-Type' : 'application/json;charset=UTF-8'
   					}
   				});
   	    		promise.success(function(data){
   					d.resolve(data);
   					if(block){
   						Metronic.unblockUI();
   					}
   				});
   	    		return d.promise;
			}
		};
	}]);
});