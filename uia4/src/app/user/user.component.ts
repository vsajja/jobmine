import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {QuoteService} from "../services/quote.service";
import {AuthenticationService} from "../core/authentication/authentication.service";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {
  username: any;
  user: any;

  shortlist: any;
  applications: any;

  constructor(private route: ActivatedRoute,
              private quoteService: QuoteService,
              private authenticationService: AuthenticationService) {
    this.username = this.route.snapshot.paramMap.get('username');
    this.getUser(this.username);

  }

  ngOnInit() {
  }

  getUser(username: string) {
    this.quoteService.getUser(username).subscribe(
      (data: any) => {
        this.user = data;
        this.getShortlist(this.user.userId);
        this.getApplications(this.user.userId);
      });
  }

  getShortlist(userId: string) {
    this.quoteService.getShortlist(userId).subscribe(
      (data: any) => {
        this.shortlist = data;
      });
  }

  apply(jobId: string) {
    this.quoteService.apply(this.user.userId, jobId);
  }

  getApplications(userId: string) {
    this.quoteService.getApplications(userId).subscribe(
      (data: any) => {
        this.applications = data;
      });
  }

  FIXME_timeAgo(value: string) {
    return this.quoteService.FIXME_timeAgo(value);
  }
}
