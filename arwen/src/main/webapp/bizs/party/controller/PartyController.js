define(['../service/PartyService.js',
        '../directive/party-tree.js',
        '../../common/service/bubble-message.js',
        'angularFileUpload'],function(){
	app.controller('PartyController',['$scope','PartyService','FileUploader','BubbleMessage',function($scope,service,FileUploader,bubbleMessage){
		$scope.party = {},$scope.dept={},$scope.refresh='',$scope.deptId="",$scope.users=[],$scope.user={};
		$scope.create = function(id,type){
			if(type == 'super'){
				$scope.partyCreate();
			}else if(type == 'party'){
				$scope.deptCreate(id,type);
			}else if(type =='dept'){
				$scope.deptCreate(id,type);
			}
		};
		
		$scope.edit = function(id,type){
			if(type == 'party'){
				$scope.partyEdit(id);
			}else if(type == 'dept'){
				$scope.deptEdit(id);
			}
		};
		
		$scope.partyCreate = function(){
			$scope.party = {
				name : '',
				logoUrl : '',
				desp : ''
			};
			$('#party-form').modal('show');
		};
		
		$scope.partyEdit = function(id){
			service.getPartyById(id).then(function(data){
				$scope.party = data;
				$('#party-form').modal('show');
			});
		};
		
		$scope.clearPartyLogo = function(){
			$scope.party.logoUrl = '';
		};
		
		var uploader = $scope.uploader = new FileUploader({
			url: uploadFile
	    });

	    // FILTERS
	    uploader.filters.push({
	        name: 'customFilter',
	        fn: function(item, options) {
	            return this.queue.length < 10;
	        }
	    });

	    uploader.onSuccessItem = function(fileItem, response, status, headers) {
	    	if(response && response.success){
	    		var edit = false;
	    		if($scope.party.id){
	    			edit = true;
	    		}
	    		$scope.party.logoUrl=response.url;
	    		Metronic.unblockUI();
	    		service.saveParty($scope.party).then(function(data){
					if(data && data.success){
						$scope.party = {
							name : '',
							logoUrl : '',
							desp : ''
						};
						ok(edit,'party');
					}
				});
	    	}
	    };
	    
	    $scope.saveParty = function(){
	    	if($scope.party.logoUrl != ''){
	    		var edit = false;
	    		if($scope.party.id){
	    			edit = true;
	    		}
	    		service.saveParty($scope.party).then(function(data){
					if(data && data.success){
						$scope.party = {
							name : '',
							logoUrl : '',
							desp : ''
						};
						ok(edit,'party');
					}
				});
	    	}else{
	    		if(uploader.getNotUploadedItems().length<=0){
		    		bubbleMessage.show({msg:'请选择logo图片',type:'danger'});
		    	}
	    		Metronic.blockUI({
	                boxed: true
	            });
		    	uploader.uploadAll();
	    	}
	    };
	    
	    $scope.deptCreate = function(id,type){
	    	$scope.dept = {
	    		name : '',
	    		status : 'ENABLE'
	    	};
	    	if(type == 'party'){
	    		$scope.dept.partyId = id;
	    	}else{
	    		$scope.dept.parentId = id;
	    	}
	    	
	    	$('#dept-form').modal('show');
	    };
	    
	    $scope.deptEdit = function(id){
	    	service.getDeptById(id).then(function(data){
	    		$scope.dept = data;
	    		$('#dept-form').modal('show');
	    	});
	    };
	    
	    $scope.saveDept = function(){
	    	var edit = false;
    		if($scope.dept.id){
    			edit = true;
    		}
    		if($scope.dept.id){
    			service.saveDept($scope.dept).then(function(data){
    				if(data && data.success){
    					ok(edit,'dept');
    				}
    			});
    		}else if($scope.dept.partyId){
    			service.addDept($scope.dept).then(function(data){
    				if(data && data.success){
    					ok(edit,'dept');
    				}
    			});
    		}else{
    			service.addChild($scope.dept).then(function(data){
    				if(data && data.success){
    					ok(edit,'dept');
    				}
    			});
    		}
	    };
	    
	    $scope.$watch('deptId',function(v){
	    	if(v != ''){
	    		service.getDeptById($scope.deptId).then(function(data){
	    			$scope.dept = data;
		    		$scope.users = data.users;
		    	});
	    	}
	    });
	    
	    $scope.addUser = function(){
	    	$scope.user={
	    		name : '',
	    		idCardNum : '',
	    		num : '',
	    		phoneNum : '',
	    		email : '',
	    		desp : ''
	    	};
	    	
	    	$("#user-form").modal('show');
	    };
	    
	    $scope.editUser = function(index){
	    	$scope.user = $scope.users[index];
	    	$("#user-form").modal('show');
	    };
	    
	    $scope.saveUser = function(){
	    	if($scope.user.id){
	    		service.saveUser($scope.user).then(function(data){
	    			if(data && data.success){
	    				bubbleMessage.show({msg:'操作成功!'});
	    				$("#user-form").modal('hide');
	    				service.getDeptById($scope.deptId).then(function(data){
	    	    			$scope.dept = data;
	    		    		$scope.users = data.users;
	    		    	});
	    			}
	    		});
	    	}else{
	    		$scope.user.deptId = $scope.dept.id;
	    		service.addUser($scope.user).then(function(data){
	    			if(data && data.success){
	    				bubbleMessage.show({msg:'操作成功!'});
	    				$("#user-form").modal('hide');
	    				service.getDeptById($scope.deptId).then(function(data){
	    	    			$scope.dept = data;
	    		    		$scope.users = data.users;
	    		    	});
	    			}
	    		});
	    	}
	    };
	    
	    var ok = function(edit,type){
	    	$('#'+type+'-form').modal('hide');
	    	bubbleMessage.show({msg:'操作成功!'});
			var d = new Date();
			if(edit){
				d = d + "#";
			}else{
				d = d + "";
			}
			$scope.refresh = d;
	    };
	}]);
});