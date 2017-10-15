'use strict';

describe('Service: authenticationservice', function () {

  // load the service's module
  beforeEach(module('jobApp'));

  // instantiate service
  var authenticationservice;
  beforeEach(inject(function (_authenticationservice_) {
    authenticationservice = _authenticationservice_;
  }));

  // it('should do something', function () {
  //   expect(!!authenticationservice).toBe(true);
  // });

});
