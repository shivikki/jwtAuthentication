import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialConfigModule } from './material-config/material-config.module';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LoginComponent } from './components/login/login.component';
import { DasboardComponent } from './components/dasboard/dasboard.component';
import { HomeComponent } from './components/home/home.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { LoginService } from './services/login.service';
import { AuthGuard } from './guards/auth.guard';
import { AuthInterceptor } from './services/auth.interceptor';
@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    DasboardComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialConfigModule,
    NgbModule,
    FormsModule,
    HttpClientModule
  ],


  providers: [{provide:HTTP_INTERCEPTORS,useClass:AuthInterceptor,multi:true}], 
  //all services mentionef. multi true used means multiple interceptor can be used
  bootstrap: [AppComponent]
})
export class AppModule { }
