import { NgModule } from '@angular/core';
import { Route } from '../core/route.service';
import { Routes, RouterModule } from '@angular/router';
import {JobComponent} from "./job.component";

const routes: Routes = Route.withShell([
  { path: 'jobs/:jobId', component: JobComponent }
]);

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  declarations: [],
  providers: []
})
export class JobRoutingModule { }
