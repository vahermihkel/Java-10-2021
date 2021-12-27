import { Category } from "./category.model";

export class Item {
  constructor(
    public title: string,
    public price: number,
    public category: Category,
    public id?: number
  ) {}
}


// export class Item2 {
//   title: string;
//   price: number;

//   constructor(
//     _title: string,
//     _price: number
//   ) {
//     this.title = _title;
//     this.price = _price;
//   }
// }