import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {RioPostService} from "../service/rio-post.service";
import {Post} from "../model/Post";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {RioCommentService} from "../service/rio-comment.service";
import {Comment} from "../model/Comment";
import {User} from "../model/User";

@Component({
  selector: 'app-rio-post-view',
  templateUrl: './rio-post-view.component.html',
  styleUrls: ['./rio-post-view.component.scss']
})
export class RioPostViewComponent implements OnInit {
  post: Post;
  loading: boolean = true;
  found: boolean = false;
  commentGroup: FormGroup;
  comments : Comment[];


  constructor(private formBuilder: FormBuilder, private rioCommentService: RioCommentService, private rioPostService: RioPostService, private activatedRoute: ActivatedRoute, private router: Router) {


  }

  ngOnInit(): void {
    // console.log(this.activatedRoute.snapshot.paramMap.keys);
    this.rioPostService.getByID(this.activatedRoute.snapshot.paramMap.get('id')).toPromise().then(r => {
      this.post = r;
      this.found = true;
      this.refreshComments();
    }).catch(reason => {
      this.found = false;
    }).finally(() => this.loading = false);


    this.commentGroup = this.formBuilder.group({
      commentText: ['', Validators.required]
    });

    // try {
    // if(this.router.getCurrentNavigation().extras.state.comment){
    //   console.log('sd'+this.router.getCurrentNavigation().extras.state.comment)
    // }
    // } catch (e) {console.log(e);
    // }
  }


  postComment() {

    let comment = new class implements Comment {
      id: number;
      postID: number;
      userID: number;
      text: string;
      user:User;
    }

    comment.postID = this.post.id;
    comment.userID = parseInt(localStorage.getItem('id'));
    comment.text = this.commentGroup.controls['commentText'].value;

    this.rioCommentService.createComment(comment).toPromise().then(value => {
      this.commentGroup.controls['commentText'].setValue(null);
      this.refreshComments();
    })
  }

  private refreshComments() {
    this.rioCommentService.getAllByPostID(this.post.id).toPromise().then(value => {
      this.comments = value;
      console.log(value)
    })
  }
}
