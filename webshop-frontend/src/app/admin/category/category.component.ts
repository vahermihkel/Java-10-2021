import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/model/category.model';
import { CategoryService } from 'src/app/service/category.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {
  categories: Category[] = [];
  message = '';

  constructor(private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.categoryService.getCategoriesFromDb().subscribe((categories) => {
      this.categories = categories;
    });
  }

  onDeleteCategory(category: Category) {
    let categoryId = category.id;
    if (categoryId) {
      this.categoryService.deleteCategoryFromDb(categoryId).subscribe((res) => {
        this.message = res.responseMessage;
        this.resetMessage();
        this.categoryService.getCategoriesFromDb().subscribe((categories) => {
          this.categories = categories;
        });
      });
    }
  }

  resetMessage() {
    setTimeout(() => {
      this.message = '';
    }, 3000);
  }
}
