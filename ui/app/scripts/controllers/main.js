'use strict';

/**
 * @ngdoc function
 * @name jobmineApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the jobmineApp
 */
angular.module('jobApp')
  .controller('MainCtrl', ['$scope', 'Restangular', 'authenticationservice', function ($scope, Restangular, authenticationservice) {
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];

    // reset login status
    authenticationservice.ClearCredentials();

    var jobmine = Restangular.all('jobmine');

    // This will query /job and return a promise.
    jobmine.customGET().then(function (jobmine) {
      $scope.jobmine = jobmine;
    });
  }]);

