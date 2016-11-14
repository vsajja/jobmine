'use strict';

/**
 * @ngdoc function
 * @name jobmineApp.controller:ProfileCtrl
 * @description
 * # ProfileCtrl
 * Controller of the jobmineApp
 */
angular.module('jobmineApp')
  .controller('ProfileCtrl', function ($scope) {
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
    $scope.tabs = [
      { title:'Dynamic Title 1', content:'Dynamic content 1' },
      { title:'Dynamic Title 2', content:'Dynamic content 2', disabled: true }
    ];

    $scope.model = {
      name: 'Tabs'
    };
  });

