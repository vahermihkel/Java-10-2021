import { HttpClient, HttpHeaders } from '@angular/common/http';
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

  addTokenToHeader() {
    let headers = new HttpHeaders();
    const token = this.getToken();
    if (token) {
      console.log("SETIN")
      headers = headers.set("Authorization", "Bearer " + token);
    }
    console.log(headers);
    return headers;
  }

  private getToken(): string | null {
    const authData = sessionStorage.getItem("authData");
    if (authData) {
      const parsedAuthData = JSON.parse(authData);
      if (parsedAuthData.token && new Date(parsedAuthData.expiration) > new Date()) {
        return parsedAuthData.token;
      }
    }
    return null;
  }
}
