import { NgModule } from '@angular/core';
import { Route } from '../core/route.service';
import { Routes, RouterModule } from '@angular/router';
import { JobsComponent } from "./jobs.component";

const routes: Routes = Route.withShell([
  { path: 'jobs', component: JobsComponent, data: { title: 'Jobs' } }
]);

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  declarations: [],
  providers: []
})
export class JobsRoutingModule { }
