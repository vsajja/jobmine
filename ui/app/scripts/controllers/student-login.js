'use strict';

/**
 * @ngdoc function
 * @name jobApp.controller:StudentLoginCtrl
 * @description
 * # StudentLoginCtrl
 * Controller of the jobApp
 */
angular.module('jobApp')
  .controller('StudentLoginCtrl', ['$scope', '$location', 'AuthenticationService', 'Restangular', function ($scope, $location, AuthenticationService, Restangular) {
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];

    // reset login status
    AuthenticationService.ClearCredentials();

    $scope.alerts = [];

    $scope.closeAlert = function (index) {
      $scope.alerts.splice(index, 1);
    };

    $scope.login = function login() {
      Restangular.one('login').customPOST($scope.user)
        .then(function (user) {
          console.log('login called!')

          AuthenticationService.SetCredentials($scope.user.username, $scope.user.password, user);
          $location.path('/home');
        }, function () {
          $scope.alerts.push({type: 'danger', msg: 'Error! Invalid credentials!'});
        });
    };
  }]);
