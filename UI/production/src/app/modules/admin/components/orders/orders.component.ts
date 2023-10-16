import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent {
  public orderData: any[]=[];
  private apiServerUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  ngOnInit() {
    // Make an HTTP request to fetch customer data
    const token = localStorage.getItem('token')
    console.log(token);
    
    this.http.get<any[]>(`${this.apiServerUrl}/order/all`,
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      })
      .subscribe(data => {
        this.orderData = data;
      });
  }



}
