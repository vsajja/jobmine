import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Http} from "@angular/http";
import {NgProgressService} from 'ngx-progressbar';

@Component({
  selector: 'app-job',
  templateUrl: './job.component.html',
  styleUrls: ['./job.component.scss']
})
export class JobComponent implements OnInit {

  jobId : string;
  job: any;

  constructor(private route: ActivatedRoute, private http: Http, private progressService: NgProgressService) {
    this.route.params.subscribe( params => console.log(params) );
  }

  ngOnInit() {
    this.getJob();
  }

  getJob() {
    this.route.params.subscribe( params => {
        this.jobId = params.jobId;

        this.progressService.start();
        this.http.get('/jobs/' + this.jobId).subscribe(res => {
          var job = res.json();
          this.job = job;
          this.progressService.done();
        });
      });
  }
}
