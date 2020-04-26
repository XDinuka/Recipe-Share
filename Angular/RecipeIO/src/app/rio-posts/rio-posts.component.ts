import {Component, Inject, OnInit} from '@angular/core';
import {Post} from "../model/Post";
import {RioPostService} from "../service/rio-post.service";
import {FormBuilder} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-rio-posts',
  templateUrl: './rio-posts.component.html',
  styleUrls: ['./rio-posts.component.scss']
})
export class RioPostsComponent implements OnInit {

  posts: Post[] = [];

  constructor( private rioPostService: RioPostService, private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.getPosts();
  }

  getPosts() {
    this.rioPostService.getByUser(localStorage.getItem('id')).subscribe(value => {
      this.posts = value;
    })
  }

  // openDialog(): void {
    // const dialogRef = this.dialog.open(RioRecipeCreateComponent, {
    //   // height: '80vh',
    //   // width: '80vw',
    //   data: {name: null, vegan: false}
    // });
    //
    // dialogRef.afterClosed().subscribe(result => {
    //   console.log(result);
    // });
  // }

  doDelete(post: Post) {
    const index = this.posts.indexOf(post, 0);
    if (index > -1) {
      this.rioPostService.deleteByID(post.id).subscribe(value => {
        this.posts.splice(index, 1);
        this.snackBar.open('Post Deleted.');
      })
    }

  }

}


