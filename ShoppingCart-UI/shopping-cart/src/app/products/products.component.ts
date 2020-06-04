import { Component, OnInit, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators'; 
import { Router } from '@angular/router';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  constructor(private httpClient: HttpClient, private router: Router) { }

  data: Object;
  mapBaseProduct = new Map<any, any>();
  @Output() cartMap;

  ngOnInit() {
    this.httpClient.get('http://localhost:8066/shoppingCart/product/list')
    .subscribe(result => {
      console.log(JSON.stringify(result));
      this.data = result;
  });
}

  addToCart(productId: any){
    console.log("Inside addToCart() method");
    
    this.httpClient.post('http://localhost:8066/shoppingCart/addProduct/'+productId,{})
    .subscribe(result => {
      console.log(JSON.stringify(result));
      this.data = result;
    });
    this.router.navigateByUrl('/cart');
  }

  goPlaces(){
    this.router.navigateByUrl('/cart');
  }
}
