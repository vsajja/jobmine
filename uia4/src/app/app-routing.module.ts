import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {JobComponent} from "./job/job.component";

const routes: Routes = [
  // { path: 'jobs/:jobId', component: JobComponent },
  { path: '**', redirectTo: '', pathMatch: 'full' }   // Fallback when no prior route is matched
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: []
})
export class AppRoutingModule { }
