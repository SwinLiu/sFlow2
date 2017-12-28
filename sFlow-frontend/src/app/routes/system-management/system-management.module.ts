import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SharedModule } from '@shared/shared.module';
import { AuthorityComponent } from "app/routes/system-management/authority/authority.component";
import { MenuComponent } from "app/routes/system-management/menu/menu.component";
import { NzTreeModule } from "ng-tree-antd";


const routes: Routes = [
    { path: 'menu', component: MenuComponent },
    { path: 'authority', component: AuthorityComponent }
];

@NgModule({
    imports: [
        SharedModule,
        NzTreeModule,
        RouterModule.forChild(routes)
    ],
    providers: [ ],
    declarations: [
        MenuComponent,
        AuthorityComponent
    ],
    exports: [
        RouterModule
    ]
})
export class SystemManagementModule { }
