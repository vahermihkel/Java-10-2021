export interface ItemOutputInterface {
    title: string;
    price: number;
    category: {
      id: number,
      categoryName?: string
    };
}