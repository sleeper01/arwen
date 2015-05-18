define(['/arwen/resources/metronic/assets/global/plugins/jstree/dist/jstree.js'],function(){
	app.directive('partyTree',['$ocLazyLoad',function($ocLazyLoad){
		return {
			restrict : 'A',
			scope : {
				create : '&',
				edit : '&',
				refresh : '=',
				deptId : '='
			},
			link : function(scope,ele,attrs){
				$ocLazyLoad.load([{
			        name:'com.qh.mainApp',
			        files : ['/arwen/resources/metronic/assets/global/plugins/jstree/dist/themes/default/style.min.css']
				}]);
				var tree = ele.jstree({
					"core" : {
		                "themes" : {
		                    "responsive": false
		                }, 
		                "check_callback" : true,
		                'data' : {
		                    'url' : "/arwen/party/getNodes.qh",
		                    'data' : function (node) {
		                      return { 'parentId' : node.id,'type' : node.li_attr?node.li_attr.type:'root'};
		                    }
		                }
		            },
		            "types" : {
		                "default" : {
		                    "icon" : "fa fa-folder icon-state-warning icon-lg"
		                },
		                "file" : {
		                    "icon" : "fa fa-file icon-state-warning icon-lg"
		                }
		            },
		            "plugins" : ["contextmenu"],
		            "contextmenu":{
		            	"items" : function (o, cb) { // Could be an object directly
		        			return {
		        				"create" : {
		        					"separator_before"	: false,
		        					"separator_after"	: true,
		        					"_disabled"			: false, //(this.check("create_node", data.reference, {}, "last")),
		        					"label"				: "创建",
		        					"action"			: function (data) {
		        						var inst = $.jstree.reference(data.reference),
		        							obj = inst.get_node(data.reference);
//		        						inst.create_node(obj, {}, "last", function (new_node) {
//		        							setTimeout(function () { inst.edit(new_node); },0);
//		        						});
		        						scope.create(obj.li_attr);
		        					}
		        				},
		        				"rename" : {
		        					"separator_before"	: false,
		        					"separator_after"	: false,
		        					"_disabled"			: false, //(this.check("rename_node", data.reference, this.get_parent(data.reference), "")),
		        					"label"				: "编辑",
		        					"action"			: function (data) {
		        						var inst = $.jstree.reference(data.reference),
		        							obj = inst.get_node(data.reference);
		        						if(obj.id == "-1")
		        							return false;
		        						scope.edit(obj.li_attr);
		        					}
		        				}/*,
		        				"remove" : {
		        					"separator_before"	: false,
		        					"icon"				: false,
		        					"separator_after"	: false,
		        					"_disabled"			: false,
		        					"label"				: "删除",
		        					"action"			: function (data) {
		        						var inst = $.jstree.reference(data.reference),
		        							obj = inst.get_node(data.reference);
		        						if(inst.is_selected(obj)) {
		        							inst.delete_node(inst.get_selected());
		        						}
		        						else {
		        							inst.delete_node(obj);
		        						}
		        					}
		        				}*/
		        			};
		        		}
		            }
				}).on("changed.jstree", function (e, data) {
					  if($('.jstree-contextmenu').length == 0 && data.node.li_attr.type == 'dept'){
						  scope.$apply(function(){
							  scope.deptId = data.node.id;
						  });
					  }
				});;
				
				scope.$watch('refresh',function(v){
					if(v != ''){
						if(v.indexOf('#') > 0){
							tree.jstree().refresh_node(tree.jstree().get_parent(tree.jstree().get_selected()));
						}else{
							tree.jstree().refresh_node(tree.jstree().get_selected());
						}
					}
				});
			}
		};
	}]);
});