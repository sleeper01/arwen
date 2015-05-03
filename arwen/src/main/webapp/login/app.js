define([
	'angular',
	'../resources/metronic/assets/global/plugins/jquery-validation/js/jquery.validate.min.js',
	'../resources/metronic/assets/admin/pages/scripts/login.js'
    ],
	function() {
		app = angular.module('com.qh.loginApp', []).config(function($provide) {
		    $provide.factory('AccountSessionService',function($http,$q) {
		        return {
		        	isLogin : function(){
		        		var d = $q.defer();
	       	    		var promise = $http({
	       			   		url : "/arwen/account/isLogin.qh",
	       					method : "POST",
	       					headers : {
	       						'Content-Type' : 'application/json;charset=UTF-8'
	       					}
	       				});
	       	    		promise.success(function(data){
	       					d.resolve(data);
	       				});
	       	    		return d.promise;
		        	}
		        };
		    });
		    
		}).run(['$rootScope','AccountSessionService',function($rootScope,service){
			service.isLogin().then(function(data){
            	if(data.isLogin){
            		window.location.href="http://"+location.host+"/arwen";
            	}
            });
		}]);
		
		app.controller('LoginController',['$scope','$http','$q','$timeout',function($scope,$http,$q,$timeout){
			$scope.account = {
				name : '',
				pwd : ''
			};
			
			$scope.submitLogin = function(){
				if ($('.login-form').validate().form()){
					$scope.login($scope.account).then(function(data){
						if(data && data.success){
							window.location.href="http://"+location.host+"/arwen";
						}
					});
				}
			};
			
			$scope.login = function(data){
				var d = $q.defer();
   	    		var promise = $http({
   			   		url : "/arwen/account/login.qh",
   					method : "POST",
   					data : data,
   					headers : {
   						'Content-Type' : 'application/json;charset=UTF-8'
   					}
   				});
   	    		promise.success(function(data){
   					d.resolve(data);
   				});
   	    		return d.promise;
			};
			
			$timeout(function(){
				$('.login-form input').unbind('keypress');
				
				$('.login-form input').keypress(function(e) {
					if (e.which == 13) {
						$scope.submitLogin();
						return false;
					}
				});
			},1000);
		}]);
	}
);