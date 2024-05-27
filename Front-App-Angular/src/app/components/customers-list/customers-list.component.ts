import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Customer } from '../../models/customer.model';
import { CustomerService } from '../../services/customer.service';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-customers-list',
  templateUrl: './customers-list.component.html',
  styleUrls: ['./customers-list.component.css']
})
export class CustomersListComponent implements OnInit {

  @ViewChild('confirmDeleteModal') confirmDeleteModalRef!: ElementRef | undefined; // Modifier la déclaration pour qu'elle puisse être undefined

  customers!: Customer[];
  customerToDelete: Customer | null = null; // Customer to be deleted
  deleteMessage: string = '';

  constructor(
    private customerService: CustomerService,
    private router: Router,
    private authService: AuthService,
  ) { }

  ngOnInit(): void {
    if (this.authService.isLoggedIn()) {
      this.getCustomers();
    }
  }

  getCustomers(): void {
    this.customerService.getAllCustomers().subscribe({
      next: data => {
        this.customers = data;
      },
      error: error => {
        console.error('An error occurred while fetching customers:', error)
      }
    });
  }

  editCustomer(id: number): void {
    this.router.navigate([`/customers/${id}/edit`]);
  }

  // Method to set the customer to be deleted
  prepareDelete(customer: Customer): void {
    this.customerToDelete = customer;
        this.deleteMessage = `Are you sure you want to delete customer <strong>${this.customerToDelete ? this.customerToDelete.name : ''}</strong>?`;
  }

  deleteCustomer(): void {
    if (this.customerToDelete) {
      this.customerService.deleteCustomer(this.customerToDelete.id).subscribe({
        next: () => {
          this.getCustomers();
        },
        error: (error) => {
          console.error('An error occurred while deleting customer:', error)
        }
      });
    }
    this.customerToDelete = null; // Reset customerToDelete
  }

  hasRole(role: string): boolean {
    return this.authService.hasRole(role);
  }

}
