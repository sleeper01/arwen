define(function(){
	app.directive('mouseHover',function(){
		return {
			restrict : 'A',
			link : function(scope,ele,attrs){
				ele.bind('mouseleave',function(event){
					$(this).removeClass(attrs.addClass);
				});
				
				ele.bind('mouseover',function(event){
					$(this).addClass(attrs.addClass);
				});
			}
		};
	});
});