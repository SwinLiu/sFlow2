import { LayoutComponent } from '../layout/layout.component';
import { LoginComponent } from './pages/login/login.component';
import { LockComponent } from './pages/lock/lock.component';
import { RegisterComponent } from './pages/register/register.component';
import { ForgetComponent } from './pages/forget/forget.component';
import { MaintenanceComponent } from './pages/maintenance/maintenance.component';
import { Page404Component } from './pages/404/404.component';
import { Page500Component } from './pages/500/500.component';
import { DashboardV1Component } from './dashboard/v1/v1.component';

export const routes = [
    {
        path: '',
        component: LayoutComponent,
        children: [
            { path: '', redirectTo: 'dashboard/v1', pathMatch: 'full' },
            { path: 'dashboard', redirectTo: 'dashboard/v1', pathMatch: 'full' },
            { path: 'dashboard/v1', component: DashboardV1Component, data: { translate: 'dashboard_v1' } },
            { path: 'logics', loadChildren: './logics/logics.module#LogicsModule' },
            { path: 'sys', loadChildren: './system-management/system-management.module#SystemManagementModule' },
            { path: 'user', loadChildren: './user-management/user-management.module#UserManagementModule' },
            { path: 'company', loadChildren: './company-management/company-management.module#CompanyManagementModule' },
            { path: 'employee', loadChildren: './employee-management/employee-management.module#EmployeeManagementModule' }
        ]
    },
    // 单页不包裹Layout
    { path: 'register', component: RegisterComponent, data: { translate: 'register' } },
    { path: 'login', component: LoginComponent, data: { title: 'login' } },
    { path: 'forget', component: ForgetComponent, data: { translate: 'forget' } },
    { path: 'lock', component: LockComponent, data: { translate: 'lock' } },
    { path: 'maintenance', component: MaintenanceComponent },
    { path: '404', component: Page404Component },
    { path: '500', component: Page500Component },
    { path: '**', component: Page404Component }
];
