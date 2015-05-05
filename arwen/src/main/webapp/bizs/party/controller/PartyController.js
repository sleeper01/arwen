define(['../service/PartyService.js',
        '../directive/party-tree.js'],function(){
	app.controller('PartyController',['$scope','PartyService',function($scope,service){
		$scope.party = {};
		$scope.partyCreate = function(){
			$scope.party = {
				name : '',
				logoUrl : '',
				desp : ''
			};
			$('#party-form').modal('show');
		};
		
		$scope.partyEdit = function(){
			
		};
	}]);
});