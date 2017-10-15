import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { JobsComponent } from './jobs.component';
import { JobsRoutingModule } from "./jobs-routing.module";

@NgModule({
  imports: [
    CommonModule,
    JobsRoutingModule
  ],
  declarations: [JobsComponent]
})
export class JobsModule { }
