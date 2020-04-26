import {Component, OnInit} from '@angular/core';
import {RioHttpClientService} from '../service/rio-http-client.service';
import {Recipe} from "../model/Recipe";
import {RioRecipeService} from "../service/rio-recipe.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-rio-recipes',
  templateUrl: './rio-recipes.component.html',
  styleUrls: ['./rio-recipes.component.scss']
})
export class RioRecipesComponent implements OnInit {

  recipes: Recipe[] = [];
  searchGroup: FormGroup;

  constructor(private rioRecipeService: RioRecipeService, private formBuilder: FormBuilder) {

  }


  ngOnInit(): void {
    this.searchGroup = this.formBuilder.group({
      name: ['', Validators.required],
      vegan: []
    });
  }

  getRecipes() {
    if(this.searchGroup.controls['vegan'].value){
    this.rioRecipeService.fetchByNameVegan(this.searchGroup.controls['name'].value).subscribe(value => {
      this.recipes = value;
    })}else{
      this.rioRecipeService.fetchByName(this.searchGroup.controls['name'].value).subscribe(value => {
        this.recipes = value;
      })
    }
  }

}
