import { Component, OnInit } from '@angular/core';
// import { NgForm } from '@angular/forms';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-jobs',
  templateUrl: './jobs.component.html',
  styleUrls: ['./jobs.component.scss']
})
export class JobsComponent implements OnInit {

  searchForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.createForm();
  }

  private createForm() {
    this.searchForm = this.formBuilder.group({
      searchTerm: ['', Validators.required],
      location: ['', Validators.required]
      // remember: true
    });
  }

  ngOnInit() {
  }

  search() {
    console.log('search jobs');
    console.log('search jobs');
    console.log('search jobs');
    console.log('search jobs');
    console.log('search jobs');
  }
}
