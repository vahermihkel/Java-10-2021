import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { User } from 'src/app/model/user.model';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm) {
    this.authService.signup(form.value).subscribe(authData => {
      if (authData.token && authData.expirationDate) {
        new User(
          authData.personCode,
          authData.firstName,
          authData.lastName,
          authData.email,
          authData.phone,
          authData.token,
          authData.expirationDate
        );
        sessionStorage.setItem("authData", JSON.stringify({
          token: authData.token,
          expiration: authData.expirationDate  
        }));
      }
    });
  }

}
