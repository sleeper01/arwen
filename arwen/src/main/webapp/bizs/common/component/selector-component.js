define(['/arwen/resources/metronic/assets/global/plugins/select2/select2.js'],function(){
	app.directive('selectorComponent',['$ocLazyLoad',function($ocLazyLoad){
		$ocLazyLoad.load([{
	        name:'com.qh.mainApp',
	        files : ['/arwen/resources/metronic/assets/global/plugins/bootstrap-select/bootstrap-select.min.css',
	                 '/arwen/resources/metronic/assets/global/plugins/select2/select2.css']
		}]);
		return {
			restrict : 'A',
			scope : {
				config : '='
			},
			link : function(scope,ele,attrs){
				scope.config.field = ele;
				ele.select2({
		            placeholder: scope.config.placeholder,
		            minimumInputLength: 1,
		            ajax: { // instead of writing the function to execute the request we use Select2's convenient helper
		                url: scope.config.url,
		                dataType: 'json',
		                delay: 250,
		                data: function (term, page) {
		                    return {
		                        q: term, // search term
		                        page_limit: 10,
		                    };
		                },
		                results: function (data, page) { // parse the results into the format expected by Select2.
		                    return {
		                        results: data
		                    };
		                }
		            },
		            escapeMarkup: function (m) {
		                return m;
		            } // we do not want to escape markup since we are displaying html in results
		        });
			}
		};
	}]);
});