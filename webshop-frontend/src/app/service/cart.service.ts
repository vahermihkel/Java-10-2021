import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Item } from '../model/item.model';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private cartItemsInService: Item[] = [];
  private cartItemsChanged = new Subject<number>();

  constructor(private http: HttpClient) { }

  url = "http://localhost:4200";
  // api_username = "92ddcfab96e34a5f";
  // account_name = "EUR3D1";
  // order_reference = Math.floor(Math.random()*99999);
  // timestamp = new Date();
  // nonce = this.api_username + this.timestamp;
  // customer_url = "https://www.example.com";


  startPayment(sumOfCart: number) {
    return this.http.post<any>(this.url, sumOfCart);
  }
  // startPayment(sumOfCart: number) {
  //   // let headers = new HttpHeaders();
  //   // headers.set("Authorization", "Basic OTJkZGNmYWI5NmUzNGE1Zjo4Y2QxOWU5OWU5YzJjMjA4ZWU1NjNhYmY3ZDBlNGRhZA==");
  //   return this.http.post<any>(this.url,{
  //     "api_username": "92ddcfab96e34a5f",
  //     "account_name": "EUR3D1",
  //     "amount": sumOfCart,
  //     "order_reference": Math.floor(Math.random()*99999),
  //     "timestamp": new Date(),
  //     "nonce": "92ddcfab96e34a5f" + new Date(),
  //     "customer_url": "https://www.example.com"
  //     },
  //     { headers: new HttpHeaders({'Authorization': 'Basic OTJkZGNmYWI5NmUzNGE1Zjo4Y2QxOWU5OWU5YzJjMjA4ZWU1NjNhYmY3ZDBlNGRhZA==' })}
  //     );
  // }

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
