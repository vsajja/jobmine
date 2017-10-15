'use strict';

/**
 * @ngdoc function
 * @name jobApp.controller:StudentLoginCtrl
 * @description
 * # StudentLoginCtrl
 * Controller of the jobApp
 */
angular.module('jobApp')
  .controller('StudentLoginCtrl', ['$scope', '$location', 'Restangular', 'authenticationservice', function ($scope, $location, Restangular, authenticationservice) {
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];

    // reset login status
    authenticationservice.ClearCredentials();

    $scope.alerts = [];

    $scope.closeAlert = function (index) {
      $scope.alerts.splice(index, 1);
    };

    $scope.loginStudent = function login() {
      Restangular.all('students/login').post($scope.student)
        .then(function (student) {
          authenticationservice.SetCredentials($scope.student.username, $scope.student.password, student);
          $location.path('/students');
          console.log('login success');
        }, function () {
          $scope.alerts.push({type: 'danger', msg: 'Error! Invalid credentials!'});
        });
    };
  }]);
