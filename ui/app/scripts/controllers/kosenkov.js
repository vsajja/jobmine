'use strict';

/**
 * @ngdoc function
 * @name jobmineApp.controller:KosenkovCtrl
 * @description
 * # KosenkovCtrl
 * Controller of the jobmineApp
 */
angular.module('jobmineApp')
  .controller('KosenkovCtrl', ['$scope', '$routeParams', 'Restangular', function ($scope, $routeParams, Restangular) {
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];

    var query = $routeParams.query;
    var kosenkov = Restangular.one('kosenkov', query);

    // This will query /kosenkov/:query and return a promise.
    kosenkov.customGET().then(function (kosenkov) {
      $scope.kosenkov = kosenkov;
    });

  }]);
