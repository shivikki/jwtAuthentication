import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialConfigModule } from './material-config/material-config.module';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { SignupComponent } from './components/pages/signup/signup.component';
import { LoginComponent } from './components/pages/login/login.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HomeComponent } from './components/pages/home/home.component';
import { ProfileComponent } from './components/pages/profile/profile.component';
import { DashboardComponent } from './components/pages/dashboard/dashboard.component';
import { AuthInterceptor, AuthInterceptorProviders } from './components/services/authInterceptor';
import { SidebarComponent } from './components/pages/sidebar/sidebar.component';
import { CategoriesAdminComponent } from './components/pages/categories-admin/categories-admin.component';
import { AddCategoriesAdminComponent } from './components/pages/add-categories-admin/add-categories-admin.component';
import { ManageQuizComponent } from './components/pages/admin/manage-quiz/manage-quiz.component';
import { AddQuizComponent } from './components/pages/admin/add-quiz/add-quiz.component';
 
import { Ng2SearchPipeModule } from 'ng2-search-filter';



@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    SignupComponent,
    LoginComponent,
    HomeComponent,
    ProfileComponent,
    DashboardComponent,
    SidebarComponent,
    CategoriesAdminComponent,
    AddCategoriesAdminComponent,
    ManageQuizComponent,
    AddQuizComponent,
  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialConfigModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    Ng2SearchPipeModule
  ],
  providers: [AuthInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
