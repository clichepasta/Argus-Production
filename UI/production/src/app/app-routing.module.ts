import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LogInComponent } from './log-in/log-in.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { AuthGuard } from './Service/authgaurd.guard';
import { SignUpComponent } from './sign-up/sign-up.component';
import { CustomerLoginComponent } from './customer-login/customer-login.component';
import { DemoComponent } from './demo/demo.component';

const routes: Routes = [
  { path: 'login', component: LogInComponent },
  { path: 'sign-up', component: SignUpComponent },
  { path: 'demo', component: DemoComponent },

  {path: 'customer-login', component: CustomerLoginComponent},

  {path: '', redirectTo: '/login', pathMatch:'full'},
  {path: 'admin', loadChildren:() => import('./modules/admin/admin.module').then((m) => m.AdminModule),canActivate:[AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
