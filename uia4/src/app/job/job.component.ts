import {Component, OnInit} from '@angular/core';
import {QuoteService} from "../services/quote.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-job',
  templateUrl: './job.component.html',
  styleUrls: ['./job.component.scss']
})
export class JobComponent implements OnInit {
  jobId: string;
  job: any;

  constructor(private route: ActivatedRoute, private quoteService: QuoteService) {
  }

  ngOnInit() {
    this.jobId = this.route.snapshot.paramMap.get('jobId');
    this.job = this.getJob(this.jobId);
  }

  getJob(jobId: string) {
    this.quoteService.getJob(jobId).subscribe(
      (data: any) => {
        this.job = data;
        console.log(this.job);
      });
  }

  FIXME_timeAgo(value: string) {
    return this.quoteService.FIXME_timeAgo(value);
  }
}
