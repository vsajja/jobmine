'use strict';

/**
 * @ngdoc function
 * @name jobApp.controller:NavCtrl
 * @description
 * # NavCtrl
 * Controller of the jobApp
 */
angular.module('jobApp')
  .controller('NavCtrl', function ($scope, $rootScope, AuthenticationService) {
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];

    $rootScope.$on('userLoggedIn', function () {
      $scope.currentUser = AuthenticationService.GetCurrentUser();
    });

    $rootScope.$on('userLoggedOut', function () {
      $scope.currentUser = AuthenticationService.GetCurrentUser();
    });

    $scope.currentUser = AuthenticationService.GetCurrentUser();
  });
