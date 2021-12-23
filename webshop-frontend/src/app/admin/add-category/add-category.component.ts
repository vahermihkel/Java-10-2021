import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { Category } from 'src/app/model/category.model';
import { CategoryService } from 'src/app/service/category.service';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css']
})
export class AddCategoryComponent implements OnInit {

  addCategoryForm!: FormGroup;

  constructor(
    private categoryService: CategoryService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.addCategoryForm = new FormGroup({
      category: new FormControl(),
    });
  }

  onSubmit() {
    if (this.addCategoryForm.valid) {
      let category = new Category(this.addCategoryForm.value.category);
      this.categoryService.addCategoryToDb(category).subscribe(() => {
        this.router.navigateByUrl('admin/kategooria');
      });
    }
  }

}
