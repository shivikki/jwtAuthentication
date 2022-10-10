import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { addCategory } from 'src/app/helper/api-constant';
import { PostServiceService } from '../../services/post-service.service';

@Component({
  selector: 'app-add-categories-admin',
  templateUrl: './add-categories-admin.component.html',
  styleUrls: ['./add-categories-admin.component.scss']
})
export class AddCategoriesAdminComponent implements OnInit {

  public submitCategory = {
    title: "",
    description: ""
  };
  public valFlag=false;
  public result:any={
    'title':'',
    'description':''
  };
  constructor(private modalService:NgbModal,private apiPost:PostServiceService) { }

  ngOnInit(): void {
  }

  submitCat(modal:any){
  
    if(this.submitCategory.title.length>0 && this.submitCategory.description.length>0){
      this.createNewCategory();
    }
    else{
      this.valFlag=true;
    }
    this.modalService.open(modal,{
      size:"md",
      keyboard:true,
      backdrop:"static",
      centered:true
    })
  }

  reset(){
    this.submitCategory.title="";
    this.submitCategory.description="";
  }

  createNewCategory(){
    this.apiPost.postJsonPassData(this.submitCategory,addCategory).subscribe(
      (data)=>{
        this.result=data;
        
        if(this.result.title=='EXIST'){
          this.valFlag=true;
        
        }
        else{
          this.valFlag=false;
          this.result.description='Category added successfully.';
          this.submitCategory.title='';
          this.submitCategory.description='';
        }
        console.log(this.result);
      },
      (error)=>{
        console.error("error in createNewCategory()",error);
      }
    )
  }
}
