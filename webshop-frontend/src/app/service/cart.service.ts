import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Item } from '../model/item.model';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private cartItemsInService: Item[] = [];
  private cartItemsChanged = new Subject<number>();

  constructor() { }

  getCartItems() {
    return this.cartItemsInService.slice();
  }

  deleteFromCart(item: Item) {
    let index = this.cartItemsInService.indexOf(item);
    this.cartItemsInService.splice(index,1);
    this.cartItemsChanged.next(this.calculateSumOfCart());
  }

  addToCart(item: Item) {
    this.cartItemsInService.push(item);
    this.cartItemsChanged.next(this.calculateSumOfCart());
  }

  emptyCart() {
    this.cartItemsInService = [];
    this.cartItemsChanged.next(this.calculateSumOfCart());
  }

  getCartItemsChangedSubject() {
    return this.cartItemsChanged;
  }

  calculateSumOfCart() {
      let sumOfCart = 0;
      this.cartItemsInService.forEach(cartItem=>sumOfCart += cartItem.price);
      return sumOfCart;
  }
}
