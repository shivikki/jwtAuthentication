import { Component, OnInit } from '@angular/core';
import { PostServiceService } from 'src/app/components/services/post-service.service';
import { getAllQuiz } from 'src/app/helper/api-constant';

@Component({
  selector: 'app-manage-quiz',
  templateUrl: './manage-quiz.component.html',
  styleUrls: ['./manage-quiz.component.scss']
})
export class ManageQuizComponent implements OnInit {

  public allQuiz:any;
  public updateQuiz:any;
  constructor(private apiPost:PostServiceService) { }

  ngOnInit(): void {
    this.getQuizList();
  }

  getQuizList(){
    this.apiPost.getMapping(getAllQuiz).subscribe(
      (data)=>{
        this.allQuiz=data;
      },
      (error)=>{
        console.error(error);
      }
    )
  }

  editQuiz(quiz:any){
    this.updateQuiz=quiz;
    console.log(quiz)
  }

}
