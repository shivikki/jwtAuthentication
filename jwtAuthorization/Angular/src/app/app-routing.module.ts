import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/pages/dashboard/dashboard.component';
import { HomeComponent } from './components/pages/home/home.component';
import { LoginComponent } from './components/pages/login/login.component';
import { ProfileComponent } from './components/pages/profile/profile.component';
import { SignupComponent } from './components/pages/signup/signup.component';
import { AdminGuard } from './components/services/admin.guard';
import { NormalGuard } from './components/services/normal.guard';

const routes: Routes = [
  {
    path:"signup",
    component:SignupComponent,
    pathMatch:'full'
  },
  {
    path:"dashboard",
    component:DashboardComponent,
    pathMatch:'full',
    // canActivate:[AdminGuard]
  },
  {
    path:"login",
    component:LoginComponent,
    pathMatch:'full'
  },
  {
    path:"",
    component:LoginComponent,
    pathMatch:'full'
  },
  {
    path:"admin",
    component:HomeComponent,
    // canActivate:[AdminGuard],
    children:[
      {
        path:"admin_profile",
        component:DashboardComponent,
        pathMatch:'full',
      }
    ]
  },
  {
    path:"profile",
    component:ProfileComponent,
    pathMatch:'full',
    canActivate:[NormalGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
