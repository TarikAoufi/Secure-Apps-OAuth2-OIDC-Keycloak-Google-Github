import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';


@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.css'
})
export class LayoutComponent implements OnInit {

  constructor(public authService: AuthService) { }

  ngOnInit(): void {
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

  getUserName(): string | undefined {
    return this.authService.getUserName();
  }

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  public login(): void {
    this.authService.login();
  }

  logout(): void {
    this.authService.logout();
  }
}

