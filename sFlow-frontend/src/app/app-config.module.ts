import { NgModule, InjectionToken } from '@angular/core';

export let APP_CONFIG = new InjectionToken<AppConfig>('app.config');

export class AppConfig {
  apiUrl : string;
  enableLog : boolean
}

export const APP_DI_CONFIG: AppConfig = {
  apiUrl: 'http://api.lyplay.com',
  enableLog : true
};

@NgModule({
  providers: [{
    provide: APP_CONFIG,
    useValue: APP_DI_CONFIG
  }]
})
export class AppConfigModule { }