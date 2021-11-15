import { Injectable } from '@angular/core';
import { Item } from '../model/item.model';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private cartItemsInService: Item[] = [];

  constructor() { }

  getCartItems() {
    return this.cartItemsInService.slice();
  }

  deleteFromCart(item: Item) {
    let index = this.cartItemsInService.indexOf(item);
    this.cartItemsInService.splice(index,1);
  }

  addToCart(item: Item) {
    this.cartItemsInService.push(item);
  }

  emptyCart() {
    this.cartItemsInService = [];
  }
}
