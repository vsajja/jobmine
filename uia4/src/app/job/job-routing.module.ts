import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {Route} from '../core/route.service';
import {JobComponent} from "./job.component";

const routes: Routes = Route.withShell([
  {path: 'jobs/:jobId', component: JobComponent, data: {title: 'Job'}}
]);

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: []
})
export class JobRoutingModule {
}
