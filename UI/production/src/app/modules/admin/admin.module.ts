import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminRoutingModule } from './admin-routing.module';
import { OrdersComponent } from './components/orders/orders.component';
import { CustomersComponent } from './components/customers/customers.component';
import { WorkFlowGeneratorComponent } from './work-flow-generator/work-flow-generator.component';
import { StatisticsComponent } from './statistics/statistics.component';
import { FooterComponent } from './components/footer/footer.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { HeaderComponent } from './components/header/header.component';
import { ProductsComponent } from './components/products/products.component';
import { ProfileComponent } from './components/profile/profile.component';
import { AboutComponent } from './components/about/about.component';


@NgModule({
  declarations: [
    OrdersComponent,
    CustomersComponent,
    WorkFlowGeneratorComponent,
    StatisticsComponent,
    FooterComponent,
    AdminDashboardComponent,
    SidebarComponent,
    HeaderComponent,
    ProductsComponent,
    ProfileComponent,
    AboutComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule
  ]
})
export class AdminModule { }
