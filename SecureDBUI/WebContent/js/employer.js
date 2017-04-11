/**
 * @author Ashish Reddy
 * @version 1.0
 * 
 * This is a javaScript App that is used to control the functionalities of the employee.html file
 */

var employer = angular.module('employerApp', []);

employer.controller('employerController', function($scope, $location, $http) {

	/*$scope.ssuser.username = getUrlParameter('username');
	$scope.ssuser.username = getUrlParameter('role');
	console.log($scope.ssuser);
	console.log("the user is: " + $scope.ssuser.username);
	console.log("the role is: " + $scope.ssuser.role);*/
	
	
	$scope.addEmployee = function() {
		var addUrl = "http://localhost:9000/employee";

		var employeeData = {
			id : 0,
			fname : $scope.employee.fname,
			lname : $scope.employee.lname,
			email : $scope.employee.email,
			department : $scope.employee.department
		};

		$scope.emps=$http.post(addUrl, employeeData);

		result.success(function(data, status, headers, config) {
			$scope.message = data;
			$scope.success = true;
		});

		result.error(function(data, status, headers, config) {
			$scope.message = data;
			$scope.success = false;
		});
	};

	$scope.getEmployees = function() {
		var getURL = "http://localhost:9000/employees";
		$scope.emps = [];
		var result = $http.get(getURL, $scope.emps);

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