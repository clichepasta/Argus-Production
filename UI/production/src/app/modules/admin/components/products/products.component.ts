import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent {

  private apiServerUrl = 'http://localhost:8080';
  public product: any[]=[];

  constructor(private http: HttpClient) { }
  
  ngOnInit() {
    // Make an HTTP request to fetch customer data
    const token = localStorage.getItem('token')
    console.log(token);
    
    this.http.get<any[]>(`${this.apiServerUrl}/product/all`,
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      })
      .subscribe(data => {
        this.product = data;
      });
  }


}
