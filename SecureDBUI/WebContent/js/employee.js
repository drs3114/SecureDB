/**
 * @author Deepak Shankar
 * @version 1.0
 * 
 * This is a javaScript App that is used to control the functionalities of the employee.html file
 */

var employee = angular.module('employeeApp', []);

employee.controller('employeeController', function($scope, $http) {
	/*$scope.sessionUser = $http.get("http://localhost:9000/sessionDetails");
	sessionStorage.setItem("userName", $scope.sessionUser.username);
	sessionStorage.setItem("userRole",$scope.sessionUser.userRole)
	$scope.user = sessionStorage.getItem("userName");
	$scope.userId = sessionStorage.getItem("userId");
	console.log("the user is : " + $scope.user);*/

	$scope.addDependent = function() {
		var addUrl = "http://localhost:9000/employee/dependent";

		var dependentData = {
			"id" : "123",
			"name" : $scope.dependent.name,
			"dependentTo" : "456",
			"relationship" : $scope.dependent.relationship
		};
		
		console.log(dependentData)

		var result = $http.post(addUrl, JSON.stringify(dependentData));

		result.success(function(data, status, headers, config) {
			alert(data);
			$scope.message = data;
			$scope.success = true;
		});

		result.error(function(data, status, headers, config) {
			$scope.message = data;
			$scope.success = false;
		});
	};

	$scope.updateDependent = function() {
		var updateURL = "http://localhost:9000/employee/dependent";

		var upDependentData = {
			id : 0,
			name : $scope.dependent.name,
			dependentTo : $scope.employee,
			relationship : $scope.dependent.relationship
		};

		var result = $http.put(updateURL, upDependentData);

		result.success(function(data, status, headers, config) {
			$scope.message = data;
			$scope.success = true;
		});

		result.error(function(data, status, headers, config) {
			$scope.message = data;
			$scope.success = false;
		});
	};

	$scope.removeDependent = function() {
		var removeURL = "http://localhost:9000/employee/dependent";

		var dependentData = {
			id : 0,
			name : $scope.dependent.name,
			dependentTo : $scope.employee,
			relationship : $scope.dependent.relationship
		};

		result.success(function(data, status, headers, config) {
			$scope.message = data;
			$scope.success = true;
		});

		result.error(function(data, status, headers, config) {
			$scope.message = data;
			$scope.success = false;
		});
	};

	$scope.getDependents = function() {
		var getURL = "http://localhost:9000/employee/";
		getURL+= sessionStorage.getItem("userId").toString()+"/dependents";

		var result = $http.get(getURL)

		result.success(function(data, status, headers, config) {
			$scope.message = data;
			$scope.success = true;
		});

		result.error(function(data, status, headers, config) {
			$scope.message = data;
			$scope.success = false;
		});
	};

});