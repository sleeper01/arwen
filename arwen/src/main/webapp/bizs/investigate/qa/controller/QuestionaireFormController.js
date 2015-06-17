define(['../service/QuestionaireService.js',
        '/arwen/bizs/common/component/date-time-picker.js',
        '/arwen/bizs/common/component/context-menu.js',
        '/arwen/bizs/common/service/bubble-message.js',
        '../controller/QuestionAddController.js',
        '../directive/selection-panel/selection-panel.js',
        '../directive/subjectivity-panel/subjectivity-panel.js'],function(){
	app.controller('QuestionaireFormController',['$scope','QuestionaireService','$state','$stateParams','BubbleMessage','$ocLazyLoad',function($scope,service,$state,$stateParams,bubbleMessage,$ocLazyLoad){
		$scope.editBaseInfo = false,$scope.topic={},$scope.curClick={},$scope.ctl={showQuesionPanel:false},$scope.typeList=[];
		$ocLazyLoad.load([{
	        name:'com.qh.mainApp',
	        files : ['/arwen/bizs/investigate/resources/qa.css']
		}]);
		service.getTypeList().then(function(data){
			$scope.typeList = data;
		});
		$scope.qa = {
			title : '',
			subtitle : '',
			desp : '',
			nameless : 'NO',
			expireDate : '',
			type : ''
		};
		$scope.load = function(){
			service.get($stateParams.id).then(function(data){
				$scope.qa = data;
				$scope.editBaseInfo = false;
			});
		};
		if($stateParams.id){
			$scope.load();
		}else{
			$scope.qa = {
				title : '',
				subtitle : '',
				desp : '',
				nameless : 'NO',
				expireDate : '',
				type : ''
			};
			$scope.editBaseInfo = true;
		}
		$scope.save = function(){
			if(!$scope.qa.id){
				service.save($scope.qa).then(function(res){
					if(res && res.success){
						bubbleMessage.show({msg:'操作成功!'});
						$state.go('qa-form',{
							id : res.data.id
						});
					}
				});
			}else{
				service.save($scope.qa).then(function(res){
					if(res && res.success){
						$scope.editBaseInfo = false;
						bubbleMessage.show({msg:'操作成功!'});
					}
				});
			}
		};
		
		$scope.goToList = function(){
			$state.go('qa-list');
		};
		
		$scope.edit = function(){
			$scope.editBaseInfo = true;
		};
		
		$scope.addTopic = function(){
			$scope.topic = {
				num : '',
				subject : '',
				sort : $scope.qa.topics?($scope.qa.topics.length + 1):1
			};
			$('#qa-topic-edit').modal('show');
		};
		
		$scope.editTopic = function(id){
			service.getTopicById(id).then(function(data){
				$scope.topic = data;
				$('#qa-topic-edit').modal('show');
			});
		};
		
		$scope.saveTopic = function(){
			if(!$scope.topic.id){
				$scope.topic.qaId=$scope.qa.id;
				service.addTopic($scope.topic).then(function(data){
					if(data && data.success){
						bubbleMessage.show({msg:'操作成功!'});
						$scope.load();
					}
				});
			}else{
				service.updateTopic($scope.topic).then(function(data){
					if(data && data.success){
						bubbleMessage.show({msg:'操作成功!'});
						$scope.load();
					}
				});
			}
		};
		
		$scope.contextOption = {
			target: '#context-menu2'
		};
		$scope.contextOption1 = {
				target: '#context-menu'
		};
		
		$scope.beforeShow = function(e){
			$scope.curClick = {
				id : e.target.attributes.getNamedItem('tid').value,
				qid :e.target.attributes.getNamedItem('qid')?e.target.attributes.getNamedItem('qid').value:'',
				type : e.target.className.indexOf('question')>0?'question':'topic'
			}
		};
		
		$scope.contextEdit = function(){
			if($scope.curClick.type == 'topic'){
				$scope.editTopic($scope.curClick.id);
			}
		};
		
		$scope.addQuestion = function(){
			$scope.ctl.showQuesionPanel = true;
		};
		
		$scope.removeQuestion = function(){
			service.deleteQuestion($scope.curClick.qid).then(function(data){
				if(data && data.success){
					bubbleMessage.show({msg:'操作成功!'});
					$scope.load();
				}
			});
		};
	}]);
});
