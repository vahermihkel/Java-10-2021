import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/auth/auth.service';
import { CartService } from 'src/app/service/cart.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  sumOfCart = 0;
  isLoggedIn = false;

  constructor(private cartService: CartService,
    private authService: AuthService,
    private router: Router) { }

  ngOnInit(): void {
    this.authService.isLoggedInObs.subscribe(loggedIn=>{
        this.isLoggedIn = loggedIn;
    })
    console.log("navbari ngOnInit läks käima");
    this.cartService.getCartItemsChangedSubject().subscribe(sum=>{
      this.sumOfCart = sum;
    });
  }

  onLogout() {
    sessionStorage.removeItem("authData");
    this.authService.isLoggedInObs.next(false);
    this.isLoggedIn = false;
    this.router.navigateByUrl("/");
  }

}
