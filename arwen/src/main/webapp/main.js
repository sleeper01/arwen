var app,authTree;
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
	    'jqueryBlockui' : 'resources/metronic/assets/global/plugins/jquery.blockui.min'
    },
    shim: {
    	'angular': ['jquery'],
		'ocLazyLoad': ['angular'],
		'uiRouterExtras' : ['angularUiRouter'],
		'angularUiRouter' : ['angular'],
        'app': ['ocLazyLoad'],
		'metronic' : ['jquery','bootstrap'],
		'layout' : ['metronic'],
		'bootstrap' : ['jquery'],
		'angularCookies':['angular'],
		'jqueryBlockui':['jquery']
    }
});

requirejs(['app','metronic','layout','jqueryBlockui'], function() {
	$(document).ready(function () {
		angular.bootstrap(document.body, ['com.qh.mainApp']);
		Metronic.init(); // init metronic core componets
		Layout.init(); // init layout
		Metronic.setAssetsPath("/arwen/resources/metronic/assets/");
	});
});