import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatIconModule} from '@angular/material/icon';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatListModule} from '@angular/material/list';
import {MatCardModule} from '@angular/material/card';
import {MatTableModule} from '@angular/material/table';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
const materialConfig=[
  MatButtonModule,
  MatInputModule,
  MatIconModule,
  MatFormFieldModule,
  MatToolbarModule,
  MatListModule,
  MatCardModule,
  MatTableModule,
  MatSlideToggleModule,
  MatExpansionModule,
  MatAutocompleteModule
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MatButtonModule,
    MatInputModule,
    MatIconModule,
    MatFormFieldModule,
    MatToolbarModule,
    MatListModule,
    MatCardModule,
    MatTableModule,
    MatSlideToggleModule,
    MatExpansionModule,
    MatAutocompleteModule,
    materialConfig
    
  ],
  exports:[materialConfig]
})
export class MaterialConfigModule { }
