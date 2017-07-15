import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent }   from './components/login/login.component';
import { PageNotFoundComponent }   from './components/not-found/not-found.component';
import { SelectivePreloadingStrategy } from './selective-preloading-strategy';

const appRoutes: Routes = [
  {
    path: '',
    loadChildren: 'app/components/main/main.module#MainModule',
    data: { preload: true }
  },
  { path: 'login',  component: LoginComponent },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(
      appRoutes,
      { preloadingStrategy: SelectivePreloadingStrategy }
  ) ],
  exports: [
    RouterModule
  ],
  providers: [
    SelectivePreloadingStrategy
  ]
})
export class AppRoutingModule {}
