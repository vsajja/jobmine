import 'rxjs/add/operator/finally';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/map';

import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {QuoteService} from '../services/quote.service';
import {AuthenticationService} from "../core/authentication/authentication.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  username: any;
  user: any;
  jobs: any;

  constructor(private quoteService: QuoteService, private authService: AuthenticationService) {
    this.username = authService.credentials.username;
    this.getUser(this.username);
    this.getJobs();
  }

  ngOnInit() {
  }

  getUser(username: string) {
    this.quoteService.getUser(username).subscribe(
      (data: any) => {
        this.user = data;
      });
  }

  getJobs() {
    this.quoteService.getJobs().subscribe(
      (data: any) => {
        this.jobs = data;
      });
  }

  shortlist(jobId: string) {
    this.quoteService.shortlist(this.user.userId, jobId);
  }

  apply(jobId: string) {
    this.quoteService.apply(this.user.userId, jobId);
  }

  FIXME_timeAgo(value: string) {
    return this.quoteService.FIXME_timeAgo(value);
  }

  // search = (text$: Observable<string>) =>
  //   text$
  //     .debounceTime(100)
  //     .map(term => !term.startsWith('@') ? []
  //       : this.playerLabels.filter((player: any) => player.playerName.toLowerCase().indexOf(term.substring(1).toLowerCase()) > -1).slice(0, 10));
  //
  // formatter = (player: any) => '[~' + player.playerName + '] ';
}
