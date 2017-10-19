import {Component, OnInit, ViewChild} from '@angular/core';
// import { NgForm } from '@angular/forms';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';
import {Http, Response} from '@angular/http';
import {DatatableComponent} from "@swimlane/ngx-datatable";
import {NgProgressService} from 'ngx-progressbar';

/**
 * An object used to get page information from the server
 */
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

  constructor(private http: Http, private progressService: NgProgressService) {
    this.page.pageNumber = 0;
    this.page.size = 20;
  }

  ngOnInit() {
    this.getJobs();
    this.setPage({ offset: 0 });
  }

  search() {
    console.log('search jobs');
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

  updateFilter(event : any) {
    const val = event.target.value.toLowerCase();

    this.progressService.start();

    // filter our data
    const temp = this.temp.filter(function (d : any) {
      return d.title.toLowerCase().indexOf(val) !== -1 ||
        d.company.toLowerCase().indexOf(val) !== -1 ||
        d.location.toLowerCase().indexOf(val) !== -1 ||
        !val;
    });

    this.rows = temp;
    this.table.offset = 0;
    this.progressService.done();
  }

  /**
   * Populate the table with new data based on the page number
   * @param page The page to select
   */
  setPage(pageInfo : any){
    this.page.pageNumber = pageInfo.offset;
    // this.serverResultsService.getResults(this.page).subscribe(pagedData => {
    //   this.page = pagedData.page;
    //   this.rows = pagedData.data;
    // });
  }
}
