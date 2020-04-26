import {Component, Input, OnInit} from '@angular/core';
import {RioRecipeCreateComponent} from "../rio-recipe-create/rio-recipe-create.component";
import {MatDialog} from "@angular/material/dialog";
import {RioPostService} from "../service/rio-post.service";
import {Post} from "../model/Post";

@Component({
  selector: 'app-rio-home',
  templateUrl: './rio-home.component.html',
  styleUrls: ['./rio-home.component.scss']
})
export class RioHomeComponent implements OnInit {

    posts:Post[]=[];



  constructor(private rioPostService:RioPostService) {


  }

  ngOnInit(): void {
    this.getPosts();
  }


  getPosts(){
    this.rioPostService.getAll().subscribe(value => {
      this.posts = value;
    })
  }




}
