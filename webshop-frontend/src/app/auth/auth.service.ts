import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthData } from '../model/auth.interface';
import { LoginData } from '../model/login.interface';
import { Person } from '../model/person.model';
import { SignupData } from '../model/signup.interface';
import { catchError } from 'rxjs/operators';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private url = "http://localhost:8080/";

  constructor(private http: HttpClient) { }

  login(loginData: LoginData): Observable<AuthData> {
    return this.http.post<AuthData>(this.url + "login", loginData)
    // .pipe(
    //   tap(resData => {
    //     sessionStorage.setItem("authData", JSON.stringify({
    //       token: resData.token,
    //       expiration: resData.expirationDate  
    //     }));
    //   })
    // );
  }

  signup(signupData: SignupData): Observable<Person> {
    return this.http.post<Person>(this.url + "signup", signupData);
  }

  checkIfLoggedIn(): boolean {
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
