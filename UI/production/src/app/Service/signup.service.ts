import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  url = "http://localhost:8080"

  constructor(private http: HttpClient) { }

  createUser(credentials:any){
    return this.http.post(`${this.url}/auth/create-user`, credentials)
  }
}
