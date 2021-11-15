import { Component, OnInit } from '@angular/core';
import { Item } from '../model/item.model';
import { CartService } from '../service/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cartItems!: Item[];
  sumOfCart = 0;
  // 1. HTML-s
  // 2. Kahes või enamas funktsioonis

  constructor(private cartService: CartService) { }

  ngOnInit(): void {
    this.cartItems = this.cartService.getCartItems();
    this.sumOfCart = this.cartService.calculateSumOfCart();
  }

  onDeleteFromCart(item: Item) {
  //  this.cartService.cartItemsInService = this.cartService.cartItemsInService.filter(
  //     itemInService => itemInService !== item
  //   );
  //   this.cartItems = this.cartService.cartItemsInService;
    this.cartService.deleteFromCart(item);
    this.cartItems = this.cartService.getCartItems();
    this.sumOfCart = this.cartService.calculateSumOfCart();
  }

  onEmptyCart() {
    this.cartService.emptyCart();
    this.cartItems = this.cartService.getCartItems();
    this.sumOfCart = 0;
  }
}
