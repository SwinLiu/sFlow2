import { enableProdMode, ViewEncapsulation } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';

import './app/core/preloader/preloader';

if (environment.production) {
  enableProdMode();
}

// platformBrowserDynamic().bootstrapModule(AppModule);

const bootstrap = () => {
  return platformBrowserDynamic().bootstrapModule(AppModule, { defaultEncapsulation: ViewEncapsulation.None });
};

if (environment.hmr) {
  
} else {
  bootstrap().then(() => {
    if ((<any>window).appBootstrap) {
      (<any>window).appBootstrap();
    }
  });
}
