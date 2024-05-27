import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { ProductsComponent } from './components/products/products.component';
import { CustomersListComponent } from './components/customers-list/customers-list.component';
import { AuthGuard } from './guards/auth.guard';
import { AddEditCustomerComponent } from './components/add-edit-customer/add-edit-customer.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'customers', component: CustomersListComponent , canActivate:[AuthGuard], data: {roles:['USER']}},
  { path: 'customers/add', component: AddEditCustomerComponent , canActivate:[AuthGuard], data: {roles:['ADMIN']}},
  { path: 'customers/:id/edit', component: AddEditCustomerComponent , canActivate:[AuthGuard], data: {roles:['ADMIN']}},
  { path: 'products', component: ProductsComponent , canActivate:[AuthGuard], data: {roles:['ADMIN']}},
  { path: 'logout', redirectTo: '/', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
