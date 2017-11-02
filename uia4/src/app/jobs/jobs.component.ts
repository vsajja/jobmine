import {Component, OnInit, ViewChild} from '@angular/core';
import {Http, Response} from '@angular/http';
import {NgProgressService} from "ngx-progressbar";
import {JobService} from "../services/job.service";
import {Router} from '@angular/router';

@Component({
  selector: 'app-jobs',
  templateUrl: './jobs.component.html',
  styleUrls: ['./jobs.component.scss']
})
export class JobsComponent implements OnInit {
  rows: any;
  temp: any;
  public model: any;

  constructor(private http: Http,
              private progressService: NgProgressService,
              private jobService: JobService,
              private router: Router) {
  }

  ngOnInit() {
    this.searchJobs(null, 'Calgary');
  }

  searchJobs(query: string, location: string) {
    this.progressService.start();
    let jobsUrl = '/jobs';
    if (query) {
      jobsUrl += ('?q=' + query);
    }

    if (location) {
      if (query) {
        jobsUrl += '&';
      }
      jobsUrl += ('?l=' + location);
    }

    this.http.get(jobsUrl).subscribe(res => {
      var jobs = res.json();
      this.rows = jobs;
      this.temp = this.rows;
      this.progressService.done();
    });
  }

  shortlist(event: any, jobId: any) {
    event.preventDefault();
    this.jobService.shortlistJob(jobId);
  }

  updateFilter(event: any) {
    const val = event.target.value.toLowerCase();

    // filter our data
    const temp = this.temp.filter(function (d: any) {
      return d.title.toLowerCase().indexOf(val) !== -1 ||
        d.company.toLowerCase().indexOf(val) !== -1 ||
        d.location.toLowerCase().indexOf(val) !== -1 || !val;
    });

    this.rows = temp;
    // this.table.offset = 0;
  }

  public viewJobDetails(jobId: any) {
    this.router.navigateByUrl('/jobs/' + jobId);
  }
}
