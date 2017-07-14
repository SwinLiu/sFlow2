import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent }   from './components/login/login.component';
import { DashboardComponent }   from './components/dashboard/dashboard.component';
import { MainErrorComponent }   from './components/main-error/main-error.component';
import { UserProfileComponent }   from './components/user-profile/user-profile.component';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'login',  component: LoginComponent },
  { path: 'dashboard',  component: DashboardComponent },
  { path: 'userProfile',  component: UserProfileComponent },
  { path: '**', component: MainErrorComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
