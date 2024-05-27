import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from '../models/customer.model';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  apiUrl: string = "http://localhost:8099/api/customers"

  constructor(private http: HttpClient, private authService: AuthService) { }

  public getAllCustomers(): Observable<Array<Customer>> {
    return this.http.get<Array<Customer>>(this.apiUrl);
  }

  public getCustomerById(id: number): Observable<Customer> {
    return this.http.get<Customer>(`${this.apiUrl}/${id}`);
  }

  public addCustomer(customer: Customer): Observable<Customer> {
    return this.http.post<Customer>(this.apiUrl + '/add', customer);
  }

  public updateCustomer(id: number, customer: Customer): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}` + `/edit`, customer);
  }

  public deleteCustomer(id: any): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`, { observe: 'response', responseType: 'text' });
  }

}
