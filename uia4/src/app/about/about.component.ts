import { Component, OnInit } from '@angular/core';

import { environment } from '../../environments/environment';
import { JobService } from "../services/job.service";
import {Http} from "@angular/http";

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.scss']
})
export class AboutComponent implements OnInit {

  version: string = environment.version;
  username: string;

  constructor(private jobService: JobService, private http: Http) { }

  ngOnInit() {
    this.student = this.getStudent();
  }

  getStudent() {
    this.http.get('/students/10').subscribe(res => {
      var studentData = res.json();

      this.username = studentData.username;
    });
  }
}
