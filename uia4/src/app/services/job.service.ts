import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

import { Injectable }   from '@angular/core';
import { Observable }   from 'rxjs/Observable';

import { Http, Response } from '@angular/http';

@Injectable()
export class JobService {
  constructor() { }

  getJob() {
    return 'software developer (dev ops)';
  }
}
