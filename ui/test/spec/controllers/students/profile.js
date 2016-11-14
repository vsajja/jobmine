'use strict';

describe('Controller: StudentsProfileCtrl', function () {

  // load the controller's module
  beforeEach(module('jobmineApp'));

  var StudentsProfileCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    StudentsProfileCtrl = $controller('StudentsProfileCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(StudentsProfileCtrl.awesomeThings.length).toBe(3);
  });
});
