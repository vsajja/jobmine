import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { JobsComponent } from './jobs.component';
import { JobsRoutingModule } from "./jobs-routing.module";
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
<<<<<<< HEAD
import {TimeAgoPipe} from 'time-ago-pipe';
import { NgProgressModule } from "ngx-progressbar";
=======
>>>>>>> 862808cfdb7cb5358f9da9052883b5e9f414a055

@NgModule({
  imports: [
    CommonModule,
<<<<<<< HEAD
    JobsRoutingModule,
    NgxDatatableModule,
    NgProgressModule
=======
    NgxDatatableModule,
    JobsRoutingModule
>>>>>>> 862808cfdb7cb5358f9da9052883b5e9f414a055
  ],
  declarations: [JobsComponent, TimeAgoPipe]
})
export class JobsModule { }
