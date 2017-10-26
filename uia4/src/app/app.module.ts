import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {TranslateModule} from '@ngx-translate/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';

import {CoreModule} from './core/core.module';
import {SharedModule} from './shared/shared.module';
import {HomeModule} from './home/home.module';
import {AboutModule} from './about/about.module';
import {LoginModule} from './login/login.module';
import {JobsModule} from "./jobs/jobs.module";
import {JobComponent} from './job/job.component';
import {JobModule} from "./job/job.module";
import { SettingsComponent } from './settings/settings.component';
import {SettingsModule} from "./settings/settings.module";

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    TranslateModule.forRoot(),
    NgbModule.forRoot(),
    CoreModule,
    SharedModule,
    HomeModule,
    AboutModule,
    LoginModule,
    JobsModule,
    JobModule,
    SettingsModule,
    AppRoutingModule
  ],
  declarations: [AppComponent, JobComponent, SettingsComponent],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
