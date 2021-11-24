import { Component, OnInit } from '@angular/core';
import { Item } from 'src/app/model/item.model';
import { ItemService } from 'src/app/service/item.service';

@Component({
  selector: 'app-view-items',
  templateUrl: './view-items.component.html',
  styleUrls: ['./view-items.component.css']
})
export class ViewItemsComponent implements OnInit {
  items!: Item[];

  constructor(private itemService: ItemService) { }

  ngOnInit(): void {
    // this.items = this.itemService.getItems();
    this.itemService.getItemsFromDb().subscribe(items => {
      this.items = items;
    });
  }

  onDeleteItem(item: Item) {
    // this.itemService.deleteItem(item);
    // this.items = this.itemService.getItems();
    let itemId = item.id;
    if (itemId) {
      this.itemService.deleteItemFromDb(itemId).subscribe(()=>{
        this.itemService.getItemsFromDb().subscribe(items => {
          this.items = items;
        });
      })
    }
  }

}
