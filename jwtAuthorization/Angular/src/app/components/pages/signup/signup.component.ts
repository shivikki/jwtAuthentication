import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { addUser } from 'src/app/helper/api-constant';
import { SignupService } from './signup.service';
import { Validators } from '@angular/forms';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})

//validation

export class SignupComponent implements OnInit {
  userRegister={
    username:"",
    password:"",
    email:"",
    phone:"",
    firstName:"",
    lastName:""
  }
  public responseRest:any;
  constructor(private signService:SignupService,private modalService: NgbModal) { }

  ngOnInit(): void {
  }

  submitRegistration(mymodal:any,userForm:any){
    console.log(this.userRegister,userForm.status)
    if(userForm.status=="VALID"){
      this.registerUserRestCall(mymodal);
    }
   
  }

  registerUserRestCall(mymodal:any){
    this.signService.addUser(this.userRegister).subscribe(
     (data)=> {
      console.log("success",data);
      this.responseRest=data;
      this.modalService.open(mymodal,{
        size:"md",
        keyboard:true,
        backdrop:"static",
        centered:true
      })

      },
      (error)=>{
        error.log(error);
        console.log("went wrong");
      }
    )
  }

}
