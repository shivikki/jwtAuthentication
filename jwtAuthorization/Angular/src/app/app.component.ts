import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'firstApp';
}
/*
STEPS AFTER ADDITION OF ANGULAR MATERIAL

1. make a new folder to keep all the compoenents

2. Inside components  folder create a new component navbar - ng g c navbar 

3. ng g c login

4. ng g c dashboard

5. ng g c home

6. In navbar-component.ts file check the selector & paste in html file of app.component.html
<app-navbar></app-navbar>

7. First design navbar component
user angualr material Toolbar

8. In style.css make commonly ised class

9. Use FormsModel import in app.modules if using ngModel and give name field also to input tag

10. to create a zservice -> create new folder service. cd <path> ng g s login

11. In service file need HttpClientModule for rest apis- import it in app.module and HttpClient in service file

12. if get error Access to XMLHttpRequest at 'http://localhost:8080/token' from origin 'http://localhost:4200' has been blocked by CORS policy
use @CrossOrigin annotation in controller

13. Make use of AuthGuarad for acces check - if token present then only able to access urls
make a folder for guards
>ng g guard auth 
select can activate

14. swlect intercept to add token in headers before calling all the rest call


*/
