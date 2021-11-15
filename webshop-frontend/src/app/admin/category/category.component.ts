import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CategoryService } from 'src/app/service/category.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {
  categories!: string[];

  constructor(private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.categories = this.categoryService.getCategories();
  }

  onDeleteCategory(category: string) {
    this.categoryService.deleteCategory(category);
  }

  onSubmit(form: NgForm) {
    if (form.valid) {
      this.categoryService.addCategory(form.value.category);
    }
  }
}
