import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

import { Injectable }   from '@angular/core';
import { Observable }   from 'rxjs/Observable';

import { Http, Response } from '@angular/http';
import {AuthenticationService} from "../core/authentication/authentication.service";
import {NgProgressService} from "ngx-progressbar";

@Injectable()
export class JobService {
  constructor(private http: Http,
              private authenticationService: AuthenticationService,
              private progressService: NgProgressService) { }

  shortlistJob(jobId : any) {
    console.log('shortlist' + jobId);

    //
    // const req = this.http.post('/students/shortlist/' + jobId, { username: this.username() })
    //   .subscribe(
    //     res => {
    //       console.log(res);
    //     }
    //   );
  }

  username() {
    const credentials = this.authenticationService.credentials;
    return credentials ? credentials.username : null;
  }
}
