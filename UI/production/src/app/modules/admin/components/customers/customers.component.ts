import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';



@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent {

  private apiServerUrl = 'http://localhost:8080';
  public customerData: any[]=[];

  constructor(private http: HttpClient) { }
  
  ngOnInit() {
    // Make an HTTP request to fetch customer data
    const token = localStorage.getItem('token')
    console.log(token);
    
    this.http.get<any[]>(`${this.apiServerUrl}/customer/all`,
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      })
      .subscribe(data => {
        this.customerData = data;
      });
  }


}
