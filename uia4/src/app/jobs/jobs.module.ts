import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { JobsComponent } from './jobs.component';
import { JobsRoutingModule } from "./jobs-routing.module";
import { NgProgressModule } from 'ngx-progressbar';
import {NgxDatatableModule} from "@swimlane/ngx-datatable";
import {TimeAgoPipeModule} from "time-ago-pipe/index";
import {TimeAgoPipe} from "time-ago-pipe";

@NgModule({
  imports: [
    CommonModule,
    JobsRoutingModule,
    NgxDatatableModule,
    NgProgressModule,
    TimeAgoPipeModule,
    JobsRoutingModule
  ],
  declarations: [JobsComponent, TimeAgoPipe]
})
export class JobsModule { }
