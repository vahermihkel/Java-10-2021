import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { Category } from '../model/category.model';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private backendUrl = 'http://localhost:8080/categories';

  constructor(private http: HttpClient,
    private authService: AuthService) {}

  getCategoriesFromDb() {
    let headers = new HttpHeaders();
    const authData = sessionStorage.getItem("authData");
    let token = "";
    if (authData) {
      const parsedAuthData = JSON.parse(authData);
      if (parsedAuthData.token && new Date(parsedAuthData.expiration) > new Date()) {
        headers = headers.append("Authorization", "Bearer " + parsedAuthData.token);
        token = parsedAuthData.token;
        console.log(headers);
      }
    }
    console.log(token);
    return this.http.get<Category[]>(this.backendUrl,
      {headers: headers});
  }

  getOneCategoryFromDb(id: number) {
    let headers = this.authService.addTokenToHeader();
    return this.http.get<Category>(this.backendUrl + '/' + id,
      {headers: headers});
  }

  addCategoryToDb(category: Category) {
    let headers = this.authService.addTokenToHeader();
    return this.http.post<{ responseMessage: string }>(
      this.backendUrl,
      category,
      {headers: headers}
    );
  }

  editCategoryInDb(category: Category) {
    let headers = this.authService.addTokenToHeader();
    return this.http.put<{ responseMessage: string }>(
      this.backendUrl,
      category,
      {headers: headers}
    );
  }

  deleteCategoryFromDb(id: number) {
    let headers = this.authService.addTokenToHeader();
    return this.http.delete<{ responseMessage: string }>(
      this.backendUrl + '/' + id,
      {headers: headers}
    );
  }
}
