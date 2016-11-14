'use strict';

/**
 * @ngdoc function
 * @name jobmineApp.controller:StudentprofileCtrl
 * @description
 * # StudentprofileCtrl
 * Controller of the jobmineApp
 */
angular.module('jobmineApp')
  .controller('StudentprofileCtrl', ['$scope', '$routeParams', 'Restangular', function ($scope, $routeParams, Restangular) {
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];

    var userName = $routeParams.userName;


    var student = Restangular.one('students', userName);

    // This will query /students/:userName and return a promise.
    student.customGET().then(function (student) {
      $scope.student = student;
    });
  }]);
