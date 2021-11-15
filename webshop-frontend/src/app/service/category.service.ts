import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private categoriesInService = ["kat1", "kat2", "kat"];

  constructor() { }

  getCategories() {
    return this.categoriesInService.slice();
  }

  addCategory(category: string) {
    this.categoriesInService.push(category);
  }

  deleteCategory(category: string) {
    let index = this.categoriesInService.indexOf(category);
    this.categoriesInService.splice(index,1);
  }
}
