define([
	'angular',
	'angularUiRouter',
    'uiRouterExtras',
    'angularCookies'
    ],
	function() {
		app = angular.module('com.qh.mainApp', ['ui.router', 'ct.ui.router.extras','oc.lazyLoad','ngCookies'])
		.config(function($locationProvider, $stateProvider, $futureStateProvider, $ocLazyLoadProvider,$provide) {

		    // Set the app module as loaded
		    $ocLazyLoadProvider.config({
		        loadedModules: ['com.qh.mainApp'],
		        debug: true,
		        jsLoader: requirejs
		    });

		    // Future state factory
		    $futureStateProvider.stateFactory('ocLazyLoad', function($q, $ocLazyLoad, futureState) {
		        var deferred = $q.defer();

		        $ocLazyLoad.load(futureState.load).then(function() {
		        	deferred.resolve();
		        }, function() {
		        	deferred.reject();
		        });

		        return deferred.promise;
		    });
		    
		 // Loading states
		    var loadAndRegisterFutureStates = function ($http,$state) {
		    	return $http({
                	url : "/arwen/account/getAccountAuth.qh",
   					method : "POST",
   					headers : {
   						'Content-Type' : 'application/json;charset=UTF-8'
   					}
                }).success(function(data){
                	if(data){
                		if(data.authApps.length > 0){
                			angular.forEach(data.authApps,function(app){
                				$stateProvider.state(app.stateName.split(":")[0],{
            	    				url : '/'+app.stateName,
            	    				templateUrl : app.templateUrl,
            	    				controller : app.ctlName,
            	    				resolve : {
            	    					controllers : [
            	    						'$ocLazyLoad', function($ocLazyLoad) {
            	    							return $ocLazyLoad.load({
            	    								name : 'com.qh.mainApp',
            	    								files : [app.ctlUrl]
            	    							});
            	    						}
            	    					]
            	    				}
            	    			});
                			});
                		}
                		authTree = data.authTree;
                	}
                }).error(function(data){
                	console.log(data);
                	window.location.href="http://"+location.host+"/arwen/login/index.html";
                });
		    };

		    $futureStateProvider.addResolve(loadAndRegisterFutureStates);
		    
		    $stateProvider
	        .state('home', {
	        	url: '/',
	        	template: '<div></div>'        	
	        });
		    
		}).run(['$rootScope','$http','$ocLazyLoad',function($rootScope,$http,$ocLazyLoad){
			$rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams){ 
                console.log("State Change: transition begins!");
                $http({
                	url : "/arwen/account/isLogin.qh",
   					method : "POST",
   					headers : {
   						'Content-Type' : 'application/json;charset=UTF-8'
   					}
                }).success(function(data){
                	if(data && data.isLogin){
                		$rootScope.account = data.account;
                	}else{
                		window.location.href="http://"+location.host+"/arwen/login/index.html";
                	}
                }).error(function(data){
                	window.location.href="http://"+location.host+"/arwen/login/index.html";
                });
	        });
			
			$rootScope.loginout = function(){
				$http({
                	url : "/arwen/account/loginout.qh",
   					method : "POST",
   					headers : {
   						'Content-Type' : 'application/json;charset=UTF-8'
   					}
                }).success(function(data){
                	window.location.href="http://"+location.host+"/arwen/login/index.html";
                }).error(function(data){
                	window.location.href="http://"+location.host+"/arwen/login/index.html";
                });
			};
		}]).controller('DesktopController', [ '$scope','$timeout','$cookieStore', function($scope,$timeout,$cookieStore) {
			$timeout(function(){
				$scope.authTree = authTree;
				if($cookieStore.get("_app_type_id") && $cookieStore.get("_app_id")){
					$timeout(function(){
						$scope.appTypeClick($cookieStore.get("_app_type_id"));
						$scope.appClick($cookieStore.get("_app_id"))
					},1000);
				}
			},1000);
			
			$scope.appTypeClick = function(id){
				if(!$(".arrow[data-id="+id+"]").hasClass("open")){
					$(".page-sidebar-menu > li").removeClass("active");
					$(".page-sidebar-menu > li").removeClass("open");
					$(".page-sidebar-menu").find("li[data-id="+id+"]").addClass("active");
					$(".page-sidebar-menu").find("li[data-id="+id+"]").addClass("open");
					$(".arrow").removeClass("open");
					$(".arrow[data-id="+id+"]").addClass("open");
				}else{
					$(".page-sidebar-menu > li").removeClass("active");
					$(".page-sidebar-menu > li").removeClass("open");
					$(".page-sidebar-menu").find("li[data-id="+id+"]").addClass("active");
					$(".arrow").removeClass("open");
					$(".page-sidebar-menu").find("li[data-id="+id+"]").removeClass("active");
				}
				$cookieStore.put("_app_type_id", id);
			};
			$scope.appClick = function(id){
				$(".sub-menu > li").removeClass("active");
				$(".page-sidebar-menu").find("li[data-id="+id+"]").addClass("active");
				$cookieStore.put("_app_id", id);
			};
		}]);
	}
);

