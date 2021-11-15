import { HttpClient } from '@angular/common/http';
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

  private backendUrl = "http://localhost:8080/items"

  constructor(private http: HttpClient) { }

  getItemsFromDb() {
    return this.http.get<Item[]>(this.backendUrl);
  }

  addItemToDb(item: Item) {
    return this.http.post(this.backendUrl, item);
  }

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

  editItem(item: Item, itemId: string) {
    let index = this.itemsInService.findIndex(itemInService => itemInService.title == itemId);
    this.itemsInService[index] = item;
  }
}
