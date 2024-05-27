import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Customer } from '../../models/customer.model';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-add-edit-customer',
  templateUrl: './add-edit-customer.component.html',
  styleUrls: ['./add-edit-customer.component.css']
})
export class AddEditCustomerComponent implements OnInit {

  formTitle: string = '';
  submitButtonLabel!: string;
  customerId!: number;
  customerForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private customerService: CustomerService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    // Retrieve customer ID from route parameters
    this.route.params.subscribe(params => {
      this.customerId = +params['id'];
    });

    this.initForm();
  }

  initForm(): void {
    this.customerForm = this.formBuilder.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]]
    });

    this.formTitle = this.customerId ? 'Edit Customer' : 'Add Customer';
    this.submitButtonLabel = this.customerId ? 'Update' : 'Save';

    // If editing, load customer data
    if (this.customerId) {
      this.loadCustomerData(this.customerId);
    }
  }

  loadCustomerData(id: number): void {
    this.customerService.getCustomerById(id).subscribe({
      next: (customer: Customer) => {
        this.customerForm.patchValue({
          name: customer.name,
          email: customer.email
        });
      },
      error: (error) => {
        console.error('An error occurred while fetching customer data:', error);
      }
    });
  }

  onSubmit(): void {
    if (this.customerForm.valid) {
      const formData = this.customerForm.value;
      const action$ = this.customerId ?
        this.customerService.updateCustomer(this.customerId, formData) :
        this.customerService.addCustomer(formData);

      action$.subscribe({
        next: () => {
          this.router.navigate(['/customers']);
        },
        error: (error) => {
          console.error('An error occurred while processing customer data:', error);
        }
      });
    }
  }

  onCancel(): void {
    this.router.navigate(['/customers']);
  }
}
