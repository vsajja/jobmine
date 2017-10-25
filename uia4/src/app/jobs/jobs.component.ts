import {Component, OnInit, ViewChild} from '@angular/core';
import {Http, Response} from '@angular/http';
import {NgProgressService} from "ngx-progressbar";
import {JobService} from "../services/job.service";
import { Router } from '@angular/router';

@Component({
  selector: 'app-jobs',
  templateUrl: './jobs.component.html',
  styleUrls: ['./jobs.component.scss']
})
export class JobsComponent implements OnInit {
  rows: any;
  temp: any;

  constructor(private http: Http,
              private progressService: NgProgressService,
              private jobService: JobService,
              private router: Router)
  {
    // this.getJobs();
  }

  ngOnInit() {
  }

  search(query: string) {
    this.progressService.start();

    this.http.get('/jobs' + '?q=' + query).subscribe(res => {
      var jobs = res.json();
      this.rows = jobs;
      this.temp = this.rows;
      this.progressService.done();
    });
  }

  getJobs() {
    this.progressService.start();
    this.http.get('/jobs').subscribe(res => {
      var jobs = res.json();

      this.rows = jobs;
      this.temp = this.rows;
      this.progressService.done();
    });
  }

  shortlist(jobId : any) {
    this.jobService.shortlistJob(jobId);
  }

  updateFilter(event : any) {
    const val = event.target.value.toLowerCase();

    // filter our data
    const temp = this.temp.filter(function (d : any) {
      return d.title.toLowerCase().indexOf(val) !== -1 ||
        d.company.toLowerCase().indexOf(val) !== -1 ||
        d.location.toLowerCase().indexOf(val) !== -1 ||
        !val;
    });

    this.rows = temp;
    // this.table.offset = 0;
  }

  public viewJobDetails(jobId : any) {
    this.router.navigateByUrl('/jobs/' + jobId);
  }
}
