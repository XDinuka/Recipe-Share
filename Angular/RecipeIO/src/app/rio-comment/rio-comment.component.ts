import {Component, Input, OnInit} from '@angular/core';
import {Comment} from "../model/Comment";

@Component({
  selector: 'app-rio-comment',
  templateUrl: './rio-comment.component.html',
  styleUrls: ['./rio-comment.component.scss']
})
export class RioCommentComponent implements OnInit {

  @Input()
  comment :Comment;

  constructor() { }

  ngOnInit(): void {
  }

}
