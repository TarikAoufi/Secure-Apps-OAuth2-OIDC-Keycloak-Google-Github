import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductsComponent } from './components/products/products.component';
import { LayoutComponent } from './components/layout/layout.component';
import { CustomersListComponent } from './components/customers-list/customers-list.component';
import { HttpClientModule } from '@angular/common/http';
import { AddEditCustomerComponent } from './components/add-edit-customer/add-edit-customer.component';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { ReactiveFormsModule } from '@angular/forms';
import { ConfirmationModalComponent } from './components/confirmation-modal/confirmation-modal.component';
import { HomeComponent } from './components/home/home.component';


export function initializeKeycloak(keycloak: KeycloakService): () => Promise<any> {
  return (): Promise<any> => {
    return new Promise(async (resolve, reject) => {
      try {
        await keycloak.init({
          config: {
            url: 'http://localhost:8080',
            realm: 'tao-realm',
            clientId: 'tao-angular-client'
          },
        //  loadUserProfileAtStartUp: true,
          initOptions: {
            onLoad: 'check-sso',
            checkLoginIframe: true
          },
          bearerExcludedUrls: []
        });
        resolve(1);
      } catch (error) {
        reject(error);
      }
    });
  };
}


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ProductsComponent,
    LayoutComponent,
    CustomersListComponent,
    AddEditCustomerComponent,
    ConfirmationModalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    KeycloakAngularModule,
    ReactiveFormsModule
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
