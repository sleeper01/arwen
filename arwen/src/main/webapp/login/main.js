var app;
requirejs.config({
    paths: {
		'jquery':'../resources/jquery/jquery-1.10.2',
        'angular': '../resources/angular/angular',
        'app':'app',
        'ocLazyLoad':'../resources/ocLazyLoad/dist/ocLazyLoad',
        'angularUiRouter' : '../resources/angular-ui-router/angular-ui-router',		
        'uiRouterExtras' : '../resources/ui-router-extras/ct-ui-router-extras',
        'metronic': '../resources/metronic/assets/global/scripts/metronic',
	    'layout': '../resources/metronic/assets/admin/layout3/scripts/layout',
	    'bootstrap' : '../resources/metronic/assets/global/plugins/bootstrap/js/bootstrap.min'
    },
    shim: {
    	'angular': ['jquery'],
		'ocLazyLoad': ['angular'],
		'uiRouterExtras' : ['angularUiRouter'],
		'angularUiRouter' : ['angular'],
        'app': ['ocLazyLoad'],
		'metronic' : ['jquery','bootstrap'],
		'layout' : ['metronic'],
		'bootstrap' : ['jquery']
    }
});

requirejs(['app','metronic','layout'], function() {
	$(document).ready(function () {
		angular.bootstrap(document.body, ['com.qh.loginApp']);
		Metronic.init(); // init metronic core componets
		Layout.init(); // init layout
		Login.init();
	});
});