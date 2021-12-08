import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthData } from '../model/auth.interface';
import { LoginData } from '../model/login.interface';
import { SignupData } from '../model/signup.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private url = "http://localhost:8080/";

  constructor(private http: HttpClient) { }

  login(loginData: LoginData) {
    return this.http.post<AuthData>(this.url + "login", loginData);
  }

  signup(signupData: SignupData) {
    return this.http.post<AuthData>(this.url + "signup", signupData);
  }

  checkIfLoggedIn() {
    const authData = sessionStorage.getItem("authData");
    if (authData) {
      const parsedAuthData = JSON.parse(authData);
      if (parsedAuthData.token && parsedAuthData.expiration > new Date()) {
        return true;
      }
    }
    return false;
  }
}
