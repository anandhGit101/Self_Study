import { Component, OnInit, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  total:number;
  dataToDisplay: any;
  data:any;
  productType = ["Books", "Apparal"];
  products = [];
  constructor(private httpClient: HttpClient, private router: Router) { }
  
  ngOnInit() {
    this.httpClient.get('http://localhost:8066/shoppingCart')
    .subscribe((result: any)=> {
      console.log(result);
      this.total = result.Total;
      console.log(result.Total);
      
      this.prepareToDisplay(result);
  });
}

remove(product: any){
  this.httpClient.delete('http://localhost:8066/shoppingCart/deleteProduct/'+product.productId,{})
  .subscribe(result => {
    this.data = result;
    this.total = this.data.Total;
  });
  var indexToDelete = this.products.indexOf(product);
  console.log(indexToDelete);
  if(product.QuantityPurchased>1){
    product.QuantityPurchased=product.QuantityPurchased-1;
  } else if(product.QuantityPurchased==1){
    this.products.splice(indexToDelete,1);
  }
}
goPlaces(){
  this.router.navigateByUrl('/product');
}

goHome(){
  this.router.navigateByUrl('/cart');
}

checkOut(){
  this.httpClient.get("http://localhost:8066/shoppingCart/checkout")
  .subscribe((result: any) =>{
    console.log(result.Total);
    this.data = result; 
  });
  this.goHome();
  /*if(.Total===0){
    this.router.navigateByUrl('/cart');
  }*/
}

prepareToDisplay(result: any){
  var dataToDisplay =JSON.parse(result.products);
      var _this = this;
      this.productType.forEach(productType => {
        if(dataToDisplay[productType]){

          JSON.parse(dataToDisplay[productType]).forEach((e,i)=>{
            var item = JSON.parse(e.item);
            item.productType = productType;
            item.QuantityPurchased = e.QuantityPurchased;
            _this.products.push(item);
          });
        }
      });
      
      console.log(this.products);
}

}
