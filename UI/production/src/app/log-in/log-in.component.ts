import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../Service/login.service';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent {

  constructor(private router: Router, private loginService:LoginService) {}


  credentials={

    email:'',
    password:''
  
  }
  
  OnSubmit(){

    // console.log("Form is submitted");
    if((this.credentials.email!='' && this.credentials.password!='') && (this.credentials.email!=null && this.credentials.password!=null)){
      // console.log("we have to give details");
      console.log("Hi");
      
     this.loginService.generateToken(this.credentials).subscribe((response:any)=>{
           console.log('Login success',response.jwtToken);
           this.loginService.loginUser(response.jwtToken)
          //  this.userRouting();
          this.loginService.getCurrentUser(response.jwtToken).subscribe((user:any)=>{

            this.loginService.setUser(user);
      
            console.log(user);
      
      
            if(this.loginService.getUserRole()=='ROLE_ADMIN'){
              window.location.href='/admin';
             }
             else if(this.loginService.getUserRole()=='ROLE_CUSTOMER'){
              window.location.href='/demo';
      
             }
      
      
           });

           
           },
      
       error=>{
         console.log('Login error',error);
       }
  
       )
  
       
     }else{
       console.log("Fields are empty");
     }

     

     
  }

  // userRouting(){

  //   this.loginService.getCurrentUser().subscribe((user:any)=>{

  //     this.loginService.setUser(user);

  //     console.log(user);


  //     if(this.loginService.getUserRole()=='ROLE_ADMIN'){
  //       window.location.href='/admin';
  //      }
  //      else if(this.loginService.getUserRole()=='ROLE_CUSTOMER'){
  //       window.location.href='/demo';

  //      }


  //    });

  // }

login(){
  console.log("clicked");
  this.router.navigate(['/sidebar']);
}



}
