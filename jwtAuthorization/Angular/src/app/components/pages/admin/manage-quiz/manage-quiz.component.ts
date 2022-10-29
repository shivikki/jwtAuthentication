import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PostServiceService } from 'src/app/components/services/post-service.service';
import { deleteQuiz, getAllCat, getAllQuiz, updateQuiz } from 'src/app/helper/api-constant';

@Component({
  selector: 'app-manage-quiz',
  templateUrl: './manage-quiz.component.html',
  styleUrls: ['./manage-quiz.component.scss']
})
export class ManageQuizComponent implements OnInit {

  public allQuiz: any;
  public updateQuiz: any;
  public successModal = false;
  public result: any;
  public allCategories: any;
  public items: any;
  public valError = false;
  categorySel = '';
  constructor(private apiPost: PostServiceService, public modalService: NgbModal) { }

  ngOnInit(): void {
    this.getQuizList();
    this.getAllCategories();
  }

  getQuizList() {
    this.apiPost.getMapping(getAllQuiz).subscribe(
      (data) => {
        this.allQuiz = data;
      },
      (error) => {
        console.error(error);
      }
    )
  }

  editQuiz(quiz: any, editQuizPop: any) {
    this.updateQuiz = quiz;
    this.valError=false;
    console.log(this.allCategories);
    this.modalService.open(editQuizPop, {
      size: "xl",
      keyboard: true,
      backdrop: "static",
      centered: true
    })
    console.log(quiz)
  }

  deleteQuiz(quiz: any, mymodal: any) {
    console.log(quiz)
    this.apiPost.postJsonPassData(quiz, deleteQuiz).subscribe(
      (data) => {
        this.result = data;
        if (this.result.responseCode == 1) {
          this.successModal = true;
        }
        else {
          this.successModal = false;
        }
        this.modalService.open(mymodal, {
          size: "md",
          keyboard: true,
          backdrop: "static",
          centered: true
        })
        this.ngOnInit();
      },
      (error) => {
        console.error("error in deleteQuiz " + error);
      }
    )
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

  selectCat(opt: any) {
    this.updateQuiz.catId = 0;
    this.valError = false;
    if (opt != null) {
      this.categorySel = opt.title;
      console.log(opt)
      for (var i = 0; i < this.allCategories.length; i++) {
        if (opt.title == this.allCategories[i].title) {
          this.updateQuiz.catId = opt.cid;
        }
      }

    }


  }
  resetCat() {
    console.log("insidr")
    this.categorySel = '';
  }

  closeModal() {
    this.modalService.dismissAll();
  }
  updateFinal(mymodal: any) {
    this.valError=false;
    if (this.updateQuiz.title != null && this.updateQuiz.title != '' && this.updateQuiz.description != null &&
      this.updateQuiz.description != '' && this.updateQuiz.noOfQues > 0 && this.updateQuiz.max_marks) {

      this.apiPost.postJsonPassData(this.updateQuiz, updateQuiz).subscribe(
        (data) => {
          this.result = data;
          if (this.result.title == 'SUCCESS') {
            this.successModal = true;
            this.result.validationStatus = this.result.description;
          
          }
          else {
            this.successModal = false;
          }
          this.modalService.open(mymodal, {
            size: "md",
            keyboard: true,
            backdrop: "static",
            centered: true
          })
        },
        (error) => {
          console.error("error in updateFinal ", error);
          this.successModal = false;
        }

      )
    }
    else{
      this.valError=true;
    }
  }

}
