import {Component, OnInit, ViewChild} from '@angular/core';
// import { NgForm } from '@angular/forms';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';
import {Http, Response} from '@angular/http';
import {DatatableComponent} from "@swimlane/ngx-datatable";

export class Page {
  //The number of elements in the page
  size: number = 0;
  //The total number of elements
  totalElements: number = 0;
  //The total number of pages
  totalPages: number = 0;
  //The current page number
  pageNumber: number = 0;
}

@Component({
  selector: 'app-jobs',
  templateUrl: './jobs.component.html',
  styleUrls: ['./jobs.component.scss']
})
export class JobsComponent implements OnInit {

  rows: any;
  temp: any;

  page = new Page();
  cache: any = {};

  @ViewChild(DatatableComponent) table: DatatableComponent;

  constructor(private http: Http) {
    this.page.pageNumber = 0;
    this.page.size = 20;
  }

  ngOnInit() {
    this.getJobs();
  }

  search() {
    console.log('search jobs');
  }

  getJobs() {
    this.http.get('/jobs').subscribe(res => {
      var jobs = res.json();

      this.rows = jobs;
      this.temp = this.rows;
    });
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
    this.table.offset = 0;
  }
}
