import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-dasboard',
  templateUrl: './dasboard.component.html',
  styleUrls: ['./dasboard.component.scss']
})
export class DasboardComponent implements OnInit {
  public fetchText:any;
  constructor(private userService:UserService) { }

  ngOnInit(): void {
  }

  getDetails(){
    this.userService.getWelcomeText().subscribe(
      (response)=>{
        this.fetchText=response;
        console.log(this.fetchText);
      },
      error=>{

      }
    )
  }

}
