'use strict';

/**
 * @ngdoc function
 * @name jobmineApp.controller:SchoolRegisterCtrl
 * @description
 * # SchoolRegisterCtrl
 * Controller of the jobmineApp
 */
angular.module('jobmineApp')
  .controller('SchoolRegisterCtrl', ['$scope', 'Restangular', function ($scope, Restangular) {
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];

    $scope.school = {};

    $scope.register = function () {
      console.log($scope.school);

      var schools = Restangular.all('schools');
      schools.post($scope.school);
    };
  }]);

