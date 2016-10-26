'use strict';

/**
 * @ngdoc function
 * @name jobmineApp.controller:JobsCtrl
 * @description
 * # JobsCtrl
 * Controller of the jobmineApp
 */
angular.module('jobmineApp')
  .controller('JobsCtrl', ['$scope', 'Restangular', function ($scope, Restangular) {
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];

    var jobs = Restangular.all('jobs');

    // This will query /jobs and return a promise.
    jobs.getList().then(function(jobs) {
      $scope.rowCollection = jobs;
    });

    //
    //$scope.rowCollection = [
    //  {
    //    firstName: 'Laurent',
    //    lastName: 'Renard',
    //    birthDate: new Date('1987-05-21'),
    //    balance: 102,
    //    email: 'whatever@gmail.com'
    //  },
    //  {
    //    firstName: 'Blandine',
    //    lastName: 'Faivre',
    //    birthDate: new Date('1987-04-25'),
    //    balance: -2323.22,
    //    email: 'oufblandou@gmail.com'
    //  },
    //  {
    //    firstName: 'Francoise',
    //    lastName: 'Frere',
    //    birthDate: new Date('1955-08-27'),
    //    balance: 42343,
    //    email: 'raymondef@gmail.com'
    //  }
    //];
  }]);
