import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { deleteCategory, getAllCat, updateCategory } from 'src/app/helper/api-constant';
import { PostServiceService } from '../../services/post-service.service';

@Component({
  selector: 'app-categories-admin',
  templateUrl: './categories-admin.component.html',
  styleUrls: ['./categories-admin.component.scss']
})
export class CategoriesAdminComponent implements OnInit {
  public allCategories: any;
  public updateCat: any = {
    'cid': '',
    'title': '',
    'description': ''
  };
  public alertFlag = false;
  public editFlag = false;
  //pagination
  page = 1;
  pageSize =5;


  constructor(private apiPost: PostServiceService, private router: Router, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.getAllCategories();
  }

  getAllCategories() {
    this.apiPost.getMapping(getAllCat).subscribe(
      (data) => {
        this.allCategories = data;
        console.log(this.allCategories, this.allCategories.length);
      },
      (error) => {
        console.error("error loading getAllCategorries" + error);
      }
    )
  }

  editCategory(popup: any, cat: any) {
    this.updateCat.cid = cat.cid;
    this.updateCat.title = cat.title;
    this.updateCat.description = cat.description;
    this.editFlag = true;
    this.modalService.open(popup, {
      size: "xl",
      keyboard: true,
      backdrop: "static",
      centered: true
    })
  }

  saveCat() {
    var update = this.updateCat;

    if (update.title == null || update.title.length == 0 || update.description == null
      || update.description.trim().length == 0) {
      this.alertFlag = true;
    }
    else {
      this.alertFlag = false;
      this.updateCategoryRest(this.updateCat);
      this.getAllCategories();
      this.modalService.dismissAll();
    }
  }

  updateCategoryRest(updateCat: any) {
    this.apiPost.postJsonPassData(updateCat, updateCategory).subscribe(
      (data) => {
        console.log(data, "output")
      },
      (error) => {
        console.error("error in updateCategoryRest", error)
      }
    )


  }

  deleteCategory(popup: any, cat: any) {
    this.updateCat.cid = cat.cid;
    this.updateCat.title = cat.title;
    this.updateCat.description = cat.description;
    this.editFlag = false;
    this.ngOnInit();
    this.modalService.open(popup, {
      size: "md",
      keyboard: true,
      backdrop: "static",
      centered: true
    })
  }

  submitDeleteCategory() {
    console.log(this.updateCat);
    this.apiPost.deleteJsonPassInt(this.updateCat.cid, deleteCategory).subscribe(
      (data) => {
        this.modalService.dismissAll();
      },
      (error) => {
        console.error("error in submitDeleteCategory" + error);
      }
    )
  }

 

}
