import { Injectable, Injector } from '@angular/core';
import { Router } from '@angular/router';
import { HttpInterceptor, HttpRequest, HttpHandler,
         HttpSentEvent, HttpHeaderResponse, HttpProgressEvent, HttpResponse, HttpUserEvent,
         HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { TokenService } from './token.service';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/mergeMap';

import { environment } from '../../../../environments/environment';
import { CONSTANTS } from "app/app.const";

/**
 * TOKEN拦截器，其注册细节见 `app.module.ts`
 */
@Injectable()
export class TokenInterceptor implements HttpInterceptor {
    constructor(private injector: Injector) {}

    private goLogin() {
        const router = this.injector.get(Router);
        this.injector.get(Router).navigate([ CONSTANTS.ROUTE_URL.signin ]);
    }

    intercept(req: HttpRequest<any>, next: HttpHandler):
        Observable<HttpSentEvent | HttpHeaderResponse | HttpProgressEvent | HttpResponse<any> | HttpUserEvent<any>> {
        let header: HttpHeaders = null;
        // 过滤授权与多assets请求
        // if (!req.url.includes('assets/') 
        //     && !req.url.includes('captcha/')
        //     && !req.url.includes('api/login/')) {
        //     // 可以进一步处理，比如：重新刷新或重新登录
        //     const authData = this.injector.get(TokenService).data;
        //     if (!authData) {
        //         this.goLogin();
        //         return Observable.create(observer => observer.error({ status: 401 }));
        //     }
        //     // 正常token值放在请求header当中，具体格式以后端为准
        //     header = req.headers.set('X-API-Token', `${authData}`)
        //         .set('Content-Type', 'application/json;charset=UTF-8')
        //         .set("dataType", "json");
        // }

        const authData = this.injector.get(TokenService).data;
        // 正常token值放在请求header当中，具体格式以后端为准
        header = req.headers.set('X-API-Token', `${authData}`)
            .set('Content-Type', 'application/json;charset=UTF-8')
            .set("dataType", "json");

        // 统一加上服务端前缀
        let url = req.url;
        if (!url.startsWith('https://') && !url.startsWith('http://')) {
            url = environment.SERVER_URL + url;
        }

        const newReq = req.clone({
            headers: header,
            url: url
        });

        return next
                .handle(newReq)
                .mergeMap((event: any) => {
                    // 允许统一对请求错误处理，这是因为一个请求若是业务上错误的情况下其HTTP请求的状态是200的情况下需要
                    if (event instanceof HttpResponse && event.status !== CONSTANTS.HTTPStatus.SUCCESS) {
                        // observer.error 会跳转至后面的 `catch`
                        // return Observable.create(observer => observer.error(event));
                    }
                    // 若一切都正常，则后续操作
                    return Observable.create(observer => observer.next(event));
                })
                .catch((res: HttpResponse<any>) => {
                    // 一些通用操作
                    switch (res.status) {
                        case CONSTANTS.HTTPStatus.UNAUTHORIZED: // 未登录状态码
                            this.goLogin();
                            break;
                        case CONSTANTS.HTTPStatus.FORBIDDEN:
                            console.log('No Access!');
                            break;
                        case CONSTANTS.HTTPStatus.GATEWAY_TIMEOUT:
                            console.log('API server timeout!');
                            break;
                        case CONSTANTS.HTTPStatus.INTERNAL_SERVER_ERROR:
                            console.log('API server 500 error!');
                            break;
                        case 404:
                            // 404
                            break;
                    }
                    // 以错误的形式结束本次请求
                    return Observable.throw(res);
                })
                ;
    }
}
