import { Injectable } from '@angular/core';
import { Item } from '../model/item.model';

@Injectable({
  providedIn: 'root'
})
export class ItemService {
  itemsInService: Item[] = [
    {title: 'Ese1', price: 100, category: "kat1"},
    {title: 'Ese2',price: 200, category: "kat2"},
    {title: 'Ese3',price: 350, category: "kat1"}
    ];

  constructor() { }

  getItems() {
    return this.itemsInService.slice();
  }

  deleteItem(item: Item) {
    let index = this.itemsInService.indexOf(item);
    this.itemsInService.splice(index,1);
  }

  addItem(item: Item) {
    this.itemsInService.push(item);
  }

  getOneItem(itemId: string) {
    return this.itemsInService.find(item => item.title == itemId);
  }
}
