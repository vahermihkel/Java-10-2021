import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Item } from '../model/item.model';
import { ItemService } from '../service/item.service';

@Component({
  selector: 'app-single-item',
  templateUrl: './single-item.component.html',
  styleUrls: ['./single-item.component.css']
})
export class SingleItemComponent implements OnInit {
  item!: Item;

  constructor(private itemService: ItemService,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    console.log(this.route);
    console.log(this.route.snapshot);
    console.log(this.route.snapshot.paramMap);
    let id = this.route.snapshot.paramMap.get("itemId");
    console.log(id);
    // let id2 = this.route.paramMap.subscribe(paramMap => {
    //   console.log(paramMap.get("itemId"));
    // })
    if (id) { // if (id != null)
      let itemFound = this.itemService.getOneItem(id);
      if (itemFound) {
        this.item = itemFound;
        console.log(this.item);
      }
    }
  }

}
