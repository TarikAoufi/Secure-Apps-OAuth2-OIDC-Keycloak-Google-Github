import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-confirmation-modal',
  templateUrl: './confirmation-modal.component.html',
  styleUrls: ['./confirmation-modal.component.css']
})
export class ConfirmationModalComponent {

  @Input() modalId: string = '';
  @Input() modalLabel: string = '';
  @Input() actionType!: 'delete' | 'edit'; // New input for action type
  @Input() title: string = '';
  @Input() htmlMessage!: string;
  @Output() confirm = new EventEmitter<void>();
  @Output() cancel = new EventEmitter<void>();

  constructor() { }

  onConfirm(): void {
    this.confirm.emit();
    this.closeModal(); // Close the modal after confirmation
  }

  onCancel(): void {
    this.cancel.emit();
    this.closeModal(); // Close the modal after cancellation
  }

   // Method for closing the modal
   closeModal(): void {
    const modalElement = document.getElementById(this.modalId);
    if (modalElement) {
      modalElement.classList.remove('show');
      modalElement.setAttribute('aria-hidden', 'true');
    }
    const modalBackdrop = document.getElementsByClassName('modal-backdrop')[0];
    if (modalBackdrop) {
      modalBackdrop.remove();
    }
  }
  
}
