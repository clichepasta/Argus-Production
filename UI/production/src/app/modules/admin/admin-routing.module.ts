import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { OrdersComponent } from './components/orders/orders.component';
import { CustomersComponent } from './components/customers/customers.component';
import { WorkFlowGeneratorComponent } from './work-flow-generator/work-flow-generator.component';
import { StatisticsComponent } from './statistics/statistics.component';
import { ProductsComponent } from './components/products/products.component';
import { AboutComponent } from './components/about/about.component';
import { ProfileComponent } from './components/profile/profile.component';

const routes: Routes = [
{path: '', component: AdminDashboardComponent, children:[
  {path: 'orders', component : OrdersComponent},
  {path: 'customers', component : CustomersComponent},
  {path: 'wfg', component : WorkFlowGeneratorComponent},
  {path: 'statistics', component : StatisticsComponent},
  {path: 'products', component : ProductsComponent},
  {path: 'about' , component : AboutComponent},
  {path: 'profile', component : ProfileComponent},
  {path:'',redirectTo:'admin', pathMatch:"full"}
]}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
