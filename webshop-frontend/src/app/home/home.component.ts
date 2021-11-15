import { Component, OnInit } from '@angular/core';
import { Item } from '../model/item.model';
import { CartService } from '../service/cart.service';
import { ItemService } from '../service/item.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  // uus Service cart-service
  // home.ts-s peab lisama uut toodet ostukorvi
  // võtame cart.ts-s service-i seest seisu
  // kuvame välja ostukorvi sisu ngFor abil cart.html-s

  // erinevad click listenerid ehk 
  // ostukorvist kustutamine
  // ostukorvi tühjendamine
  // ostukorvi kogusumma kokkuliitmine

  // samamoodi admin vaatest:
  // esemete kuvamine
  // esemete kustutamine

  // URL parameetrid
  // edit form - ngForm edasiarendus


  items!: Item[];

  constructor(private itemService: ItemService,
    private cartService: CartService) { }

//   itemService: ItemService; <----- this.
// constructor(_itemService: ItemService) { 
//   this.itemService = _itemService;
// }

  ngOnInit(): void {
    this.items = this.itemService.itemsInService;
    console.log("tulin home componenti");
  }

  onAddToCart(item: Item) {
    this.cartService.addToCart(item);
  }

}
