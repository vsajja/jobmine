'use strict';

/**
 * @ngdoc overview
 * @name jobmineApp
 * @description
 * # jobmineApp
 *
 * Main module of the application.
 */
angular
  .module('jobmineApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ui.bootstrap',
    'smart-table',
    'restangular'
  ])
  .config(function ($routeProvider, RestangularProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/jobs', {
        templateUrl: 'views/jobs.html',
        controller: 'JobsCtrl',
        controllerAs: 'jobs'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl',
        controllerAs: 'about'
      })
      .when('/schools', {
        templateUrl: 'views/schools.html',
        controller: 'SchoolsCtrl',
        controllerAs: 'schools'
      })
      .when('/students', {
        templateUrl: 'views/students.html',
        controller: 'StudentsCtrl',
        controllerAs: 'students'
      })
      .when('/companies', {
        templateUrl: 'views/companies.html',
        controller: 'CompaniesCtrl',
        controllerAs: 'companies'
      })
      .when('/settings', {
        templateUrl: 'views/developers.html',
        controller: 'SettingsCtrl',
        controllerAs: 'settings'
      })
      .when('/students/:studentId', {
        templateUrl: 'views/student.html',
        controller: 'StudentProfileCtrl',
        controllerAs: 'studentProfile'
      })
      .when('/companies/:companyId', {
        templateUrl: 'views/company.html',
        controller: 'CompanyProfileCtrl',
        controllerAs: 'companyProfile'
      })
      .when('/jobs/:jobId', {
        templateUrl: 'views/job.html',
        controller: 'JobProfileCtrl',
        controllerAs: 'jobProfile'
      })
      .when('/mines/:mineId', {
        templateUrl: 'views/mine.html',
        controller: 'MineProfileCtrl',
        controllerAs: 'mineProfile'
      })
      .when('/schools/:schoolId', {
        templateUrl: 'views/school.html',
        controller: 'SchoolProfileCtrl',
        controllerAs: 'schoolProfile'
      })
      .when('/mines', {
        templateUrl: 'views/mines.html',
        controller: 'MinesCtrl',
        controllerAs: 'mines'
      })
      .otherwise({
        redirectTo: '/'
      });

    // TODO: for dev point to Ratpack server
    // RestangularProvider.setBaseUrl('/api/v1');
    RestangularProvider.setBaseUrl('http://localhost:5050/api/v1');
  });

