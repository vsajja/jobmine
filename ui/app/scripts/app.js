'use strict';

/**
 * @ngdoc overview
 * @name jobApp
 * @description
 * # jobmineApp
 *
 * Main module of the application.
 */
var jobApp = angular
  .module('jobApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ui.bootstrap',
    'smart-table',
    'restangular',
    'xeditable',
    'base64',
    'LocalStorageModule'
  ])
  .config(function ($routeProvider, $locationProvider, $compileProvider, RestangularProvider) {
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
      .when('/register', {
        templateUrl: 'views/register.html',
        controller: 'RegisterCtrl',
        controllerAs: 'register'
      })
      .when('/student/register', {
        templateUrl: 'views/student-register.html',
        controller: 'StudentRegisterCtrl',
        controllerAs: 'studentRegister'
      })
      .when('/school/register', {
        templateUrl: 'views/school-register.html',
        controller: 'SchoolRegisterCtrl',
        controllerAs: 'schoolRegister'
      })
      .when('/company/register', {
        templateUrl: 'views/company-register.html',
        controller: 'CompanyRegisterCtrl',
        controllerAs: 'companyRegister'
      })
      .when('/companies/:companyId/job/create', {
        templateUrl: 'views/job-create.html',
        controller: 'JobCreateCtrl',
        controllerAs: 'jobCreate'
      })
      .when('/student/login', {
        templateUrl: 'views/student-login.html',
        controller: 'StudentLoginCtrl',
        controllerAs: 'studentLogin'
      })
      .otherwise({
        redirectTo: '/'
      });

    $locationProvider.html5Mode(false);
    $locationProvider.hashPrefix('');

    // release
    // $compileProvider.debugInfoEnabled(false);
    // RestangularProvider.setBaseUrl('/api/v1');

    // dev
    RestangularProvider.setBaseUrl('http://localhost:5050/api/v1');
  });

jobApp.run(function (editableOptions, $rootScope, $location, $cookieStore, $http) {
  editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'

  // keep user logged in after page refresh
  $rootScope.globals = $cookieStore.get('globals') || {};
  if ($rootScope.globals.currentUser) {
    $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
  }
});
