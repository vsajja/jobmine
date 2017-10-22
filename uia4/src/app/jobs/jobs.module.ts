import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { JobsComponent } from './jobs.component';
import { JobsRoutingModule } from "./jobs-routing.module";
import { NgProgressModule } from 'ngx-progressbar';
import {NgxDatatableModule} from "@swimlane/ngx-datatable";

@NgModule({
  imports: [
    CommonModule,
    JobsRoutingModule,
    NgxDatatableModule,
    NgProgressModule,
    JobsRoutingModule
  ],
  declarations: [JobsComponent]
})
export class JobsModule { }
