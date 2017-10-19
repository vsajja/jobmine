import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { JobsComponent } from './jobs.component';
import { JobsRoutingModule } from "./jobs-routing.module";
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import {TimeAgoPipe} from 'time-ago-pipe';
import { NgProgressModule } from "ngx-progressbar";

@NgModule({
  imports: [
    CommonModule,
    JobsRoutingModule,
    NgxDatatableModule,
    NgProgressModule
  ],
  declarations: [JobsComponent, TimeAgoPipe]
})
export class JobsModule { }
