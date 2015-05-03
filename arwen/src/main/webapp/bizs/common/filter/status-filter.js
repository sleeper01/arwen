define(function(){
	app.filter('status',function(){
		return function(input){
			if(input == 'ENABLE'){
				return '启用';
			}
			return '停用';
		};
	});
});