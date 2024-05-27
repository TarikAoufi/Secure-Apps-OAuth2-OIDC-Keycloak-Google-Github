import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import { KeycloakProfile } from 'keycloak-js';

@Injectable({
  providedIn: 'root'
})
export class AuthService implements OnInit {

  public profile?: KeycloakProfile;

  constructor(
    private http: HttpClient,
    private keycloakService: KeycloakService
  ) {
    this.loadUserProfile();
  }

  ngOnInit() {
    this.init();
  }

  /**
   * Initializes Keycloak authentication and subscribes to Keycloak events.
   * Whenever an authentication state change occurs, it loads the user profile.
   */
  init() {
    // Subscribe to Keycloak events
    this.keycloakService.keycloakEvents$.subscribe({
      next: (e) => {
        // Load user profile whenever authentication state changes
        this.loadUserProfile();
      },
      error: err => {
        console.error(err);
      }
    });
  }

  /**
   * Loads the user profile if the user is logged in.
   * 
   * @returns void
   */
  private loadUserProfile() {
    if (this.isLoggedIn()) {
      this.keycloakService.loadUserProfile().then(profile => {
        this.profile = profile;
      }).catch(error => {
        console.error('Error loading user profile:', error);
      });
    } else {
      console.warn('User is not logged in. Cannot load user profile.');
    }
  }

  getUserRoles(): string[] {
    return this.keycloakService.getUserRoles();
  }

  public isLoggedIn(): boolean {
    return this.keycloakService.isLoggedIn();
  }

  getUserName(): string | undefined {
    return this.profile?.username;
  }

  hasRole(role: string): boolean {
    const userRoles = this.keycloakService.getUserRoles();
    return userRoles.includes(role);
  }

  public hasRoleIn(roles: string[]): boolean {
    let userRoles = this.keycloakService.getUserRoles();
    for (let role of roles) {
      if (userRoles.includes(role)) return true;
    }
    return false;
  }

  async login() {
    await this.keycloakService.login({
      redirectUri: window.location.origin
    });
  }

  async logout() {
    await this.keycloakService.logout(window.location.origin)
    this.login() // Redirect to login page
  }

}
