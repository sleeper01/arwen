define(function(){
	app.filter('levelFilter',function(){
		return function(input){
			if(input == 'FIRST_LEVEL'){
				return '一级';
			}
			return '二级';
		};
	});
});