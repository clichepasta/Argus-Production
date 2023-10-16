import { Component } from '@angular/core';
import { LoginService } from 'src/app/Service/login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  constructor(private loginService:LoginService){}

  logoutUser(){
    this.loginService.logOut()
    location.reload();
    window.location.href="/login"
  }

}
