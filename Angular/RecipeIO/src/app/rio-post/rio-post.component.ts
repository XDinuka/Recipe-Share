import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Post} from "../model/Post";
import {Router} from "@angular/router";

@Component({
  selector: 'app-rio-post',
  templateUrl: './rio-post.component.html',
  styleUrls: ['./rio-post.component.scss']
})
export class RioPostComponent implements OnInit {

  @Input()
  post: Post;

  @Input()
  showRecipe: boolean = true;
  @Input()
  showComment: boolean = true;
  @Input()
  showDelete: boolean = false;
  @Output()
  deleteEvent:EventEmitter<Post> = new EventEmitter<Post>();

  // config:any= {
  //   post:{},
  //   recipe:true,
  //   comment:true
  // };



  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  toComment() {
    //
    // this.router.navigate(['post/'+this.config.post.id], { state: { comment: true } });
    this.router.navigate(['post/1'], {state: {comment: true}});
  }

  sendDelete() {
    this.deleteEvent.emit(this.post);
  }
}
