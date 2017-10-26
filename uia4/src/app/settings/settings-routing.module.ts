import {NgModule} from '@angular/core';
import {Route} from '../core/route.service';
import {Routes, RouterModule} from '@angular/router';
import {SettingsComponent} from "./settings.component";

const routes: Routes = Route.withShell([
  {path: 'settings', component: SettingsComponent, data: {title: 'Settings'}}
]);

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  declarations: [],
  providers: []
})
export class SettingsRoutingModule {
}
