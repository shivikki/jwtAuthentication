import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCategoriesAdminComponent } from './add-categories-admin.component';

describe('AddCategoriesAdminComponent', () => {
  let component: AddCategoriesAdminComponent;
  let fixture: ComponentFixture<AddCategoriesAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddCategoriesAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddCategoriesAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
