'use strict';

/**
 * @ngdoc function
 * @name jobmineApp.controller:ProfileCtrl
 * @description
 * # ProfileCtrl
 * Controller of the jobmineApp
 */
angular.module('jobmineApp')
  .controller('ProfileCtrl', function ($scope, $window) {
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
    $scope.tabs = [
      { title:'Dynamic Title 1', content:'Dynamic content 1' },
      { title:'Dynamic Title 2', content:'Dynamic content 2', disabled: true }
    ];

    $scope.alertMe = function() {
      setTimeout(function() {
        $window.alert('You\'ve selected the alert tab!');
      });
    };

    $scope.model = {
      name: 'Tabs'
    };
  });

