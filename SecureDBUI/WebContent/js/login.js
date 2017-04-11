/**
 * @author Deepak Shankar
 * @version 1.0
 * 
 * This is a javaScript App that is used to control the functionalities of the employee.html file
 */

var employee = angular.module('employeeApp', []);

employee.controller('employeeController', function($scope, $http) {

	$scope.getDependents = function() {
		var getURL = "http://localhost:9000/login";

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