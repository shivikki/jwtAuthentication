import { Component, NgModule, OnInit } from '@angular/core';
import { FormControl, FormsModule } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { PostServiceService } from 'src/app/components/services/post-service.service';
import { addNewQuiz, getAllCat } from 'src/app/helper/api-constant';
@Component({
  selector: 'app-add-quiz',
  templateUrl: './add-quiz.component.html',
  styleUrls: ['./add-quiz.component.scss']
})

export class AddQuizComponent implements OnInit {

  public addCat: any = {
    title: "",
    description: "",
    max_marks: 0,
    noOfQues: 0,
    enabled: false,
    catId: 0
  }
  public allCategories: any;
  items: string[] = [];
  categorySel = '';

  public valError=false;
  public valFlag=false;
  public valMsg='';
  public addQuizResult:any;
  constructor(private apiPost: PostServiceService,private modalService:NgbModal) {
  }


  ngOnInit(): void {
    this.getAllCategories();
  }

  selectCat(opt: any) {
    this.addCat.catId=0;
    this.valError=false;
    if (opt != null) {
      this.categorySel = opt.title;
      console.log(opt)
      for (var i = 0; i < this.allCategories.length; i++) {
        if (opt.title == this.allCategories[i].title) {
          this.addCat.catId=opt.cid;
        }
      }

    }


  }
  resetCat() {
    console.log("insidr")
    this.categorySel = '';
  }

  createQuiz(mymodal:any) {
    console.log(this.addCat);
    this.validateQuiz(mymodal);
  }

  getAllCategories() {
    this.apiPost.getMapping(getAllCat).subscribe(
      (data) => {
        this.allCategories = data;
        this.items = [];
        for (var i = 0; i < this.allCategories.length; i++) {
          this.items.push(this.allCategories[i].title);
        }

      },
      (error) => {
        console.error("error loading getAllCategorries" + error);
      }
    )
  }

  validateQuiz(mymodal:any){
    this.valError=false;
    this.valMsg='';

    //val for title & description
    if(this.addCat.title==null || this.addCat.title.length==0 || this.addCat.description==null
      || this.addCat.length==0 ){
        this.valError=true;
        this.valMsg="Fill all the mandatory fields."
        return;
    }
    if(this.addCat.catId==0 || this.addCat.max_marks==0 || this.addCat.noOfQues==0 ){
      this.valError=true;
      this.valMsg="Fill all the mandatory fields.";
      return;
    }
    this.valError=false;
    this.apiPost.postJsonPassData(this.addCat,addNewQuiz).subscribe(
      (data)=>{
        console.log(data,"submit");
        this.addQuizResult=data;
        if(this.addQuizResult.responseCode==1){
          this.valFlag=false;

        }
        else{
          this.valFlag=true;
        }
        this.modalService.open(mymodal,{
          size:"md",
          keyboard:true,
          backdrop:"static",
          centered:true
        })
      },
      (error)=>{
        console.error(error);
      }
    )
    
  }

}
