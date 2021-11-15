import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Item } from 'src/app/model/item.model';
import { ItemService } from 'src/app/service/item.service';

@Component({
  selector: 'app-edit-item',
  templateUrl: './edit-item.component.html',
  styleUrls: ['./edit-item.component.css']
})
export class EditItemComponent implements OnInit {
  editItemForm!: FormGroup;
  originalItem!: Item;

  constructor(private route: ActivatedRoute,
    private itemService: ItemService,
    private router: Router) { }

  ngOnInit(): void {
    let id = this.route.snapshot.paramMap.get("itemId");
    console.log(id);
    if (id) {
      let item = this.itemService.getOneItem(id);
      console.log(item);
      if (item) {
        this.originalItem = item;
        this.editItemForm = new FormGroup({
          title: new FormControl(item.title),
          price: new FormControl(item.price),
          category: new FormControl(item.category),
        });
      }
    }
  }

  onSubmit() {
    if (this.editItemForm.valid) {
      let formValue = this.editItemForm.value;
      let item = new Item(
        formValue.title, 
        formValue.price, 
        formValue.category);
      this.itemService.editItem(item, this.originalItem.title);
      this.router.navigateByUrl("/admin/esemed");
    }
  }

}
