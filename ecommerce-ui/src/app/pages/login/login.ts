import { Component } from '@angular/core';
import { userService } from '../services/userService';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-login',
  standalone:true,
  imports: [CommonModule, FormsModule,HttpClientModule],
  templateUrl: './login.html',
  styleUrl: './login.scss'
})
export class LoginComponent  {
email = '';
  password = '';
  errorMessage = '';
  successMessage = '';
  loading = false;

  constructor(private userService: userService) {}

  onLogin() {
    if (!this.email || !this.password) {
      this.errorMessage = 'Please fill in all fields';
      return;
    }

    this.loading = true;
    this.errorMessage = '';
    this.successMessage = '';

    this.userService.login(this.email, this.password).subscribe({
      next: (response) => {
        console.log('Login success:', response);
        this.successMessage = 'Login successful! Redirecting...';
        this.loading = false;

        // Save token to local storage
        localStorage.setItem('token', response.access_token);

        // Redirect after 2 seconds
        setTimeout(() => {
          window.location.href = '/dashboard';
        }, 2000);
      },
      error: (err) => {
        console.error('Login error:', err);
        this.errorMessage = 'Invalid email or password';
        this.loading = false;
      },
    });
  }
}
