/**
 * @author Deepak Shankar
 * @version 1.0
 * 
 * This is a javaScript App that is used to control the functionalities of the employee.html file
 */

var employee = angular.module('employeeApp', []);

employee.controller('employeeController', [ '$scope', '$http', function($scope, $http) {

	$scope.addDependent = function() {
		var addUrl = "http://localhost:9000/employee/dependent";

		var dependentData = {
			id : 0,
			name : $scope.dependent.name,
			dependentTo : $scope.employee,
			relationship : $scope.dependent.relationship
		};

		var result = $http.post(addUrl, dependentData);

		result.success(function(data, status, headers, config) {
			$scope.message = data;
			$scope.success = true;
		});

		result.error(function(data, status, headers, config) {
			$scope.message = data;
			$scope.success = false;
		})
	};

	$scope.updateDependent = function() {};

	$scope.removeDependent = function() {};

	$scope.getDependents = function() {};

} ]);