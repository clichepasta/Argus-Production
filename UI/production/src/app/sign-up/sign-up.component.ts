import { Component } from '@angular/core';
import { LoginService } from '../Service/login.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent {

  userfield={
    customer_id: '',
    email: '',
    customer_name:'',
    company_name: '',
    customerPassword:'',
    role: [
      {
          roleName: 'ROLE_CUSTOMER'
         
      }
  ],
  }

  credentials={

    email:'',
    password:''
  
  } 

  
  constructor(private loginService:LoginService){}

  ngOnInit(){
    console.log("Hello")
  }

  onSubmit(){

    if((this.userfield.email!='' && this.userfield.company_name!='' && this.userfield.customer_name!='' && this.userfield.customer_id!='' && this.userfield.customerPassword!='') && (this.userfield.email!=null && this.userfield.company_name!=null && this.userfield.customerPassword!=null && this.userfield.customer_id!=null && this.userfield.customer_name!=null)){
      // console.log("we have to give details");
     
      this.loginService.createUser(this.userfield).subscribe((response:any)=>{
        console.log('sign up success');
        window.location.href="/login"
        
    });
  
    }else{
      console.log("Fields are empty");
    }

    
    
    


  }

}
