var app,authTree,uploadFile="http://"+location.host+"/arwen/file/upload.qh";
requirejs.config({
    paths: {
		'jquery':'resources/jquery/jquery-1.10.2',
        'angular': 'resources/angular/angular',
        'angularCookies' : 'resources/angular/angular-cookies',
        'app':'app',
        'ocLazyLoad':'resources/ocLazyLoad/dist/ocLazyLoad',
        'angularUiRouter' : 'resources/angular-ui-router/angular-ui-router',		
        'uiRouterExtras' : 'resources/ui-router-extras/ct-ui-router-extras',
        'metronic': 'resources/metronic/assets/global/scripts/metronic',
	    'layout': 'resources/metronic/assets/admin/layout3/scripts/layout',
	    'bootstrap' : 'resources/metronic/assets/global/plugins/bootstrap/js/bootstrap.min',
	    'jqueryBlockui' : 'resources/metronic/assets/global/plugins/jquery.blockui.min',
	    'angularFileUpload':'resources/metronic/assets/global/plugins/angularjs/plugins/angular-file-upload/angular-file-upload',
	    'ngInfiniteScroll' : 'resources/angular/ng-infinite-scroll'
    },
    shim: {
    	'angular': {deps : ['jquery']},
		'ocLazyLoad': {deps : ['angular']},
		'uiRouterExtras' : {deps : ['angularUiRouter']},
		'angularUiRouter' : {deps : ['angular']},
        'app': {deps : ['ocLazyLoad','angularFileUpload']},
		'metronic' : {deps : ['jquery','bootstrap']},
		'layout' : {deps : ['metronic']},
		'bootstrap' : {deps : ['jquery']},
		'angularCookies':{deps : ['angular']},
		'jqueryBlockui':{deps : ['jquery']},
		'angularFileUpload' : {deps : ['angular']},
		'ngInfiniteScroll' : {deps : ['angular']}
    }
});

requirejs(['angularFileUpload'],function(){
	requirejs(['app','metronic','layout','jqueryBlockui','ngInfiniteScroll'], function() {
		$(document).ready(function () {
			angular.bootstrap(document.body, ['com.qh.mainApp']);
			Metronic.init(); // init metronic core componets
			Layout.init(); // init layout
			Metronic.setAssetsPath("/arwen/resources/metronic/assets/");
		});
	});
});