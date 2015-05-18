define(function(){
	app.filter('questionType',function(){
		return function(input){
			if(input == 'SINGLE_SELECTION'){
				return '单选';
			}else if(input == 'MULTI_SELECTIION'){
				return '多选';
			}else if(input == 'SUBJECTIVITY'){
				return '主观';
			}else if(input == 'SORT'){
				return '排序';
			}else if(input == 'MARK'){
				return '评价';
			}else if(input == 'GRADE'){
				return '打分';
			}
		};
	});
});