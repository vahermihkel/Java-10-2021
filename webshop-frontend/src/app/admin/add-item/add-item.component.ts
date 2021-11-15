import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Item } from 'src/app/model/item.model';
import { CategoryService } from 'src/app/service/category.service';
import { ItemService } from 'src/app/service/item.service';

@Component({
  selector: 'app-add-item',
  templateUrl: './add-item.component.html',
  styleUrls: ['./add-item.component.css']
})
export class AddItemComponent implements OnInit {
  // kas 1. HTML-s või 
  // 2. kahes või enamas funktsioonis
  // (muidu teen let muutujaks)
  categories!: string[];

  constructor(private itemService: ItemService,
    private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.categories = this.categoryService.getCategories();
  }

  onSubmit(form: NgForm) {
    if (form.valid) {
      console.log(form);
      let item = new Item(form.value.pealkiri, form.value.hind, form.value.kategooria);
      this.itemService.itemsInService.push(item);
    }
  }
}
