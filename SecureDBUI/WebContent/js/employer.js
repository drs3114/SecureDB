/**
 * @author Ashish Reddy
 * @version 1.0
 * 
 * This is a javaScript App that is used to control the functionalities of the employee.html file
 */

var employee = angular.module('employerApp', []);

employee.controller('employerController', [ '$scope', '$http', function($scope, $http) {

	$scope.addEmployee = function() {
		var addUrl = "http://localhost:9000/employer/employee";

		var employeeData = {
			id : 0,
			name : $scope.employee.name,
			employeeTo : $scope.employer,
			relationship : $scope.employee.relationship
		};

		var result = $http.post(addUrl, employeeData);

		result.success(function(data, status, headers, config) {
			$scope.message = data;
			$scope.success = true;
		});

		result.error(function(data, status, headers, config) {
			$scope.message = data;
			$scope.success = false;
		});
	};

	$scope.updateemployee = function() {
		var updateURL = "http://localhost:9000/employer/employee";

		var employeeData = {
			id: 0,
			name : $scope.employee.name,
			employeeTo : $scope.employer,
			relationship : $scope.employee.relationship
		};

		var result = $http.put(updateURL, employeeData);

		result.success(function(data, status, headers, config) {
			$scope.message = data;
			$scope.success = true;
		});

		result.error(function(data, status, headers, config) {
			$scope.message = data;
			$scope.success = false;
		});
	};

	$scope.removeEmployee = function() {
		var removeURL = "http://localhost:9000/employer/employee";

		var employeeData = {
			id: 0,
			name : $scope.employee.name,
			employeeTo : $scope.employer,
			relationship : $scope.employee.relationship
		};

		var result = $http.delete(removeURL, employeeData);

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
		var getURL = "http://localhost:9000/employer/employee";

		var employeeData = {
			id: 0,
			name : $scope.employee.name,
			employeeTo : $scope.employer,
			relationship : $scope.employee.relationship
		};

		var result = $http.get(getURL, employeeData);

		result.success(function(data, status, headers, config) {
			$scope.message = data;
			$scope.success = true;
		});

		result.error(function(data, status, headers, config) {
			$scope.message = data;
			$scope.success = false;
		});
	};

} ]);
