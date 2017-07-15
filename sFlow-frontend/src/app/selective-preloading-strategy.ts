import 'rxjs/add/observable/of';
import { Injectable } from '@angular/core';
import { PreloadingStrategy, Route } from '@angular/router';
import { Observable } from 'rxjs/Observable';

import { LoggerService }   from './services/logger.service';

@Injectable()
export class SelectivePreloadingStrategy implements PreloadingStrategy {
  preloadedModules: string[] = [];

  constructor(private loggerService : LoggerService) { }

  preload(route: Route, load: () => Observable<any>): Observable<any> {
    if (route.data && route.data['preload']) {
      // add the route path to the preloaded module array
      this.preloadedModules.push(route.path);

      // log the route path to the console
      this.loggerService.log('Preloaded: ' + route.path);

      return load();
    } else {
      return Observable.of(null);
    }
  }
}
