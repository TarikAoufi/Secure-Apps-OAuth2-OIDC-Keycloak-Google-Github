import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  constructor(private authService: AuthService) { 
  }
  
  getUserRoles(): string[] {
    return this.authService.getUserRoles();
  }
  
  hasRole(role: string): boolean {
    return this.authService.hasRole(role);
  }

  public hasRoleIn(roles: string[]): boolean {
    return this.authService.hasRoleIn(roles);
  }

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

}
