import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Token } from '@angular/compiler';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  url = "http://localhost:8080"

  constructor(private http: HttpClient) { }

  generateToken(credentials: any) {
    // console.log(this.http.post(`${this.url}/auth/login`,credentials));
    return this.http.post(`${this.url}/auth/login`, credentials);
  }

  public getCurrentUser()
 {

  var token= this.getToken();
 return this.http.get(`${this.url}/customer/current-user`,{
  headers: {
    Authorization: `Bearer ${token}`
  }
});
 }

  createUser(credentials:any){

    return this.http.post(`${this.url}/auth/create-user`, credentials);
    
  }

  loginUser(token: any) {
    localStorage.setItem("token", token);
    return true;
  }
  isLoggedIn() {
    let token = localStorage.getItem("token");
    if (token == undefined || token === '' || token == null) {
      return false;
    }
    else {
      return true;
    }
  }

  getUserRole(){
    let user=this.getUser();
    return user.role[0].roleName ;

  }

  setUser(user:any){
    localStorage.setItem('user',JSON.stringify(user))
  }

  getUser(){
    let userStr= localStorage.getItem('user');

    if(userStr!=null){
      return JSON.parse(userStr)
    }
    else{
      return null;
    }

  }
  //for user logout
  logOut() {
    localStorage.removeItem('token');
    console.log("token removed")
    return true;
  }

  //for get token
  getToken() {
    return localStorage.getItem("token");
  }
}
