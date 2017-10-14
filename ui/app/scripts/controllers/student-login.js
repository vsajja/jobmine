'use strict';

/**
 * @ngdoc function
 * @name jobApp.controller:StudentLoginCtrl
 * @description
 * # StudentLoginCtrl
 * Controller of the jobApp
 */
angular.module('jobApp')
  .controller('StudentLoginCtrl', ['$scope', '$location', 'Restangular', function ($scope, $location, Restangular) {
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];

    // reset login status
    // AuthenticationService.ClearCredentials();

    $scope.alerts = [];

    $scope.closeAlert = function (index) {
      $scope.alerts.splice(index, 1);
    };

    $scope.login = function login() {
      Restangular.all('students/login').post($scope.student)
        .then(function (student) {
          // AuthenticationService.SetCredentials($scope.user.username, $scope.user.password, user);
          $location.path('/students');
        }, function () {
          $scope.alerts.push({type: 'danger', msg: 'Error! Invalid credentials!'});
        });
    };
  }]);
