import {Injectable} from '@angular/core';
import {HttpEvent, HttpInterceptor, HttpHandler, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {environment} from "../../../environments/environment";

@Injectable()
export class APIInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let apiReq = null;

    if(req.url.includes('/login') ||
      req.url.includes('/register')
    ) {
      apiReq = req.clone({ url: environment.serverUrl + req.url });
    }
    else {
      apiReq = req.clone({ url: environment.serverUrl + environment.apiUrl + req.url });
    }

    return next.handle(apiReq);
  }
}
