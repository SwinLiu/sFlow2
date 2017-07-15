import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { MainComponent }   from './main.component';
import { DashboardComponent }   from '../dashboard/dashboard.component';
import { UserProfileComponent }   from '../user-profile/user-profile.component';

const mainRoutes: Routes = [
  {
    path: '',
    component: MainComponent,
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      {
        path: 'dashboard',
        component: DashboardComponent
      },
      {
        path: 'userProfile',
        component: UserProfileComponent
      }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(mainRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class MainRoutingModule { }
