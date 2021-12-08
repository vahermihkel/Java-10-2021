import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { User } from 'src/app/model/user.model';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  message = "";

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm) {
    this.authService.login(form.value).subscribe(authData => {
      if (authData.token && authData.expirationDate) {
        // new User(
        //   authData.personCode,
        //   authData.firstName,
        //   authData.lastName,
        //   authData.email,
        //   authData.phone,
        //   authData.token,
        //   authData.expirationDate
        // );
        sessionStorage.setItem("authData", JSON.stringify({
          token: authData.token,
          expiration: authData.expirationDate  
        }));
      }
    });
  }

}
