import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {TranslateModule} from '@ngx-translate/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {CoreModule} from './core/core.module';
import {HomeModule} from './home/home.module';
import {LoginModule} from './login/login.module';
import {UserComponent} from './user/user.component';
import {UsersComponent} from './users/users.component';
import {UsersModule} from "./users/users.module";
import {UserModule} from "./user/user.module";
import {NgxDatatableModule} from "@swimlane/ngx-datatable";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {APIInterceptor} from "./core/http/http-interceptor";
import {PagerService} from "./services/pager.service";
import {DecimalPipe} from '@angular/common';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    TranslateModule.forRoot(),
    NgbModule.forRoot(),
    CoreModule,
    HomeModule,
    LoginModule,
    UserModule,
    UsersModule,
    NgxDatatableModule,
    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    UserComponent,
    UsersComponent
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: APIInterceptor,
    multi: true,
  },
    DecimalPipe,
    PagerService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
