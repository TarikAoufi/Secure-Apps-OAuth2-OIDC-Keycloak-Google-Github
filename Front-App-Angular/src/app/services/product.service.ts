import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../models/product.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  apiUrl: string = "http://localhost:8090/api/products"
  
  constructor(private http: HttpClient) { }

  public getAllProducts(): Observable<Array<Product>> {
    return this.http.get<Array<Product>>(this.apiUrl);
  }
}
