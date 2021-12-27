import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm } from '@angular/forms';
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
  message = "";
  id!: number;

  constructor(private route: ActivatedRoute,
    private itemService: ItemService,
    private router: Router) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get("itemId"));
    console.log(this.id);
    if (this.id) {
      this.itemService.getOneFromDb(this.id).subscribe(item => {
        if (item) {
          console.log(item);
          this.originalItem = item;
          this.editItemForm = new FormGroup({
            title: new FormControl(item.title),
            price: new FormControl(item.price),
            category: new FormControl(item.category.categoryName),
          });
        }
      });  
    }
  }

  onSubmit() {
    if (this.editItemForm.valid) {
      let formValue = this.editItemForm.value;
      let item = new Item(
        formValue.title, 
        formValue.price, 
        formValue.category,
        this.id);
      // this.itemService.editItem(item, this.originalItem.title);
      this.itemService.editItemFromDb(item).subscribe(
        res => {
          this.message = res.responseMessage;
          this.restartForm();
          this.router.navigateByUrl("/admin/esemed");
        },
        err => {
          this.message = err.error.message;
          this.restartForm();
        })
    }
  }

  restartForm() {
    this.editItemForm.reset();
    setInterval(()=>{
      this.message = "";
    },3000)
  }

}
