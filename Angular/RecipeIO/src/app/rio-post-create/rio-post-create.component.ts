import {Component, OnInit, ViewChild} from '@angular/core';
import {Router} from "@angular/router";
import {Recipe} from "../model/Recipe";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {RioRecipeService} from "../service/rio-recipe.service";
import {MatStepper} from "@angular/material/stepper";
import {Post} from "../model/Post";
import {User} from "../model/User";
import {RioPostComponent} from "../rio-post/rio-post.component";
import {RioPostService} from "../service/rio-post.service";

@Component({
  selector: 'app-rio-post-create',
  templateUrl: './rio-post-create.component.html',
  styleUrls: ['./rio-post-create.component.scss']
})
export class RioPostCreateComponent implements OnInit {

  post:Post;
  // selectedRecipe: Recipe;
  selectRecipeGroup1: FormGroup;
  selectRecipeGroup2: FormGroup;
  selectRecipeGroup3: FormGroup;
  enterPostGroup: FormGroup;
  recipeList: Recipe[] = [];
  @ViewChild('stepper') private myStepper: MatStepper;
  // @ViewChild('postPreview') private postPreview: RioPostComponent;


  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private rioRecipeService: RioRecipeService,
    private rioPostService: RioPostService

  ) {
    this.post = new class implements Post {
      id: number;
      image: string;
      recipe: Recipe;
      text: string;
      user:User= new class implements User {
        id: number = parseInt(localStorage.getItem('id'));
        password: string = null;
        username: string = localStorage.getItem('username');
      };
    }

    // this.rioDataSharingService.currentUser.subscribe(value => this.post.user = value);
    try {
      this.post.recipe = this.router.getCurrentNavigation().extras.state.recipe;
    } catch (e) {
    }

  }

  ngOnInit(): void {


    this.selectRecipeGroup1 = this.formBuilder.group({
      name: ['', Validators.required]
    });
    this.selectRecipeGroup2 = this.formBuilder.group({
      recipeId: ['', Validators.required]
    });
    this.selectRecipeGroup3 = this.formBuilder.group({
      recipe: [(this.post.recipe!=null)?this.post.recipe.id:'', Validators.required]
    });
    this.enterPostGroup = this.formBuilder.group({
      postText: ['', Validators.required]
    });



  }

  searchRecipe(name: string) {
    if (name != null && name.length > 3) {
      this.rioRecipeService.fetchByName(name).toPromise()
        .then(value => this.recipeList = value)
        .catch(reason => this.recipeList = []);
    }
  }

  selectRecipe() {
    for (let i = 0; i < this.recipeList.length; i++) {
      let recipe = this.recipeList[i];
      if (this.selectRecipeGroup2.controls['recipeId'].value == recipe.id) {
        this.post.recipe = recipe;
        this.selectRecipeGroup3.controls['recipe'].setValue(recipe.id);
      }
    }

  }

  imageSubmitted(file: File) {
    let reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload =  ()=> {
      // this.postPreview.post = this.post;
      this.myStepper.next();
      this.post.image = reader.result.toString();

    };
    // reader.onerror = function (error) {
    //   console.log('Error: ', error);
    // };
  }

  // pr() {
  //   console.log(this.post);
  //
  // }

  doPost() {
    this.post.text = this.enterPostGroup.controls['postText'].value;
    this.rioPostService.createPost(this.post).toPromise().then(value => {
      this.router.navigate(['post', value.id])
    })
  }
}
