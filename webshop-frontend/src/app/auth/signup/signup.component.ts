import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginData } from 'src/app/model/login.interface';
import { SignupData } from 'src/app/model/signup.interface';
import { User } from 'src/app/model/user.model';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  message = "";

  constructor(private authService: AuthService,
    private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm) {
    this.message = "";
    if (form.valid) {
      const formValue = form.value;
      if (formValue.password != formValue.passwordRepeat) {
        this.message = "Paroolid ei ühti!";
        return;
      }

      const signupData: SignupData = {
        personCode: formValue.personCode,
        firstName: formValue.firstName,
        lastName: formValue.lastName,
        email: formValue.email,
        phone: formValue.phone,
        password: formValue.password
      }
  
      this.authService.signup(signupData).subscribe(
        person => {
          if (person.email && person.password) {
            const loginData: LoginData = {
              email: person.email,
              password: person.password
            }
            this.authService.login(loginData).subscribe(authData => {
              sessionStorage.setItem("authData", JSON.stringify({
                token: authData.token,
                expiration: authData.expirationDate  
              }));
              this.router.navigateByUrl("/");
            })
          }
        },
        errorRes => {
          console.log(errorRes);
          this.message = errorRes.error.message;
        }
        );
    }
   
  }

}
