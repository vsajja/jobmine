'use strict';

describe('Controller: StudentprofileCtrl', function () {

  // load the controller's module
  beforeEach(module('jobmineApp'));

  var StudentprofileCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    StudentprofileCtrl = $controller('StudentprofileCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(StudentprofileCtrl.awesomeThings.length).toBe(3);
  });
});
