import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthData } from '../model/auth.interface';
import { LoginData } from '../model/login.interface';
import { SignupData } from '../model/signup.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private url = "http://localhost:8080";

  constructor(private http: HttpClient) { }

  login(loginData: LoginData) {
    return this.http.post<AuthData>(this.url,loginData);
  }

  signup(signupData: SignupData) {
    return this.http.post<AuthData>(this.url,signupData);
  }
}
