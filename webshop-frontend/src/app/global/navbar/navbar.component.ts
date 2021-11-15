import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { CartService } from 'src/app/service/cart.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  sumOfCart = 0;

  constructor(private cartService: CartService) { }

  ngOnInit(): void {
    console.log("navbari ngOnInit läks käima");
    this.cartService.getCartItemsChangedSubject().subscribe(sum=>{
      this.sumOfCart = sum;
    });
  }

}
