import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddCategoryComponent } from './admin/add-category/add-category.component';
import { AddItemComponent } from './admin/add-item/add-item.component';
import { AdminHomeComponent } from './admin/admin-home/admin-home.component';
import { CategoryComponent } from './admin/category/category.component';
import { EditItemComponent } from './admin/edit-item/edit-item.component';
import { ViewItemsComponent } from './admin/view-items/view-items.component';
import { AuthGuard } from './auth/auth.guard';
import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';
import { CartComponent } from './cart/cart.component';
import { HomeComponent } from './home/home.component';
import { SingleItemComponent } from './single-item/single-item.component';

// localhost:4200/admin --> admin.component.html-i koos admin.component.css ja admin.component.ts failidega

const routes: Routes = [
  { path: "", component: HomeComponent },
  { path: "ostukorv", component: CartComponent },
  { path: "ese/:itemId", component: SingleItemComponent },
  { path: "admin", component: AdminHomeComponent, canActivate: [AuthGuard] },
  { path: "admin/kategooria", component: CategoryComponent, canActivate: [AuthGuard]  },
  { path: "admin/lisa-ese", component: AddItemComponent, canActivate: [AuthGuard]  },
  { path: "admin/muuda-ese/:itemId", component: EditItemComponent, canActivate: [AuthGuard]  },
  { path: "admin/esemed", component: ViewItemsComponent, canActivate: [AuthGuard]  },
  { path: 'admin/add-category', component: AddCategoryComponent, canActivate: [AuthGuard]  },
  { path: "logi-sisse", component: LoginComponent },
  { path: "registreeru", component: SignupComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
