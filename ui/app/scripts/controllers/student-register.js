'use strict';

/**
 * @ngdoc function
 * @name jobmineApp.controller:StudentRegisterCtrl
 * @description
 * # StudentRegisterCtrl
 * Controller of the jobmineApp
 */
angular.module('jobApp')
  .controller('StudentRegisterCtrl', ['$scope', 'Restangular', function ($scope, Restangular) {
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];

    $scope.student = {};
    $scope.alerts = [];

    $scope.registerStudent = function () {
      var students = Restangular.all('students');

      students.post($scope.student).then(function (addedStudent) {
        $scope.alerts.push({type: 'success', msg: 'Success! Registered student! Username: ' + addedStudent.username});
      }, function () {
        $scope.alerts.push({type: 'danger', msg: 'Error! Unable to register student.'});
      });
    };

    $scope.closeAlert = function (index) {
      $scope.alerts.splice(index, 1);
    };
  }]);
