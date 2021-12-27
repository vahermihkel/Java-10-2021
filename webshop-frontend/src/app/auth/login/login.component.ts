import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  message = "";

  constructor(private authService: AuthService,
    private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm) {
    this.authService.login(form.value).subscribe(authData => {
      if (authData.token && authData.expirationDate) {
        sessionStorage.setItem("authData", JSON.stringify({
          token: authData.token,
          expiration: authData.expirationDate  
        }));
        this.authService.isLoggedInObs.next(true);
        this.router.navigateByUrl("/admin");
      }
    });
  }

}
