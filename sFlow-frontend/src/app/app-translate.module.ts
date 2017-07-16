import { NgModule }             from '@angular/core';
import { TranslateLoader, TranslateModule} from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { Http }    from '@angular/http';

export function createTranslateHttpLoader(http: Http) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

@NgModule({
  imports: [ TranslateModule.forRoot({
    loader: {
      provide: TranslateLoader,
      useFactory: (createTranslateHttpLoader),
      deps: [Http]
    }
  }) ],
  exports: [ TranslateModule ],
  providers: [ ]
})
export class AppTranslateModule {}
