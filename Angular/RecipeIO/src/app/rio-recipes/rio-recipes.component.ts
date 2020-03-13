import { Component, OnInit } from '@angular/core';
import {Recipe, RioHttpClientService} from '../service/rio-http-client.service';

@Component({
  selector: 'app-rio-recipes',
  templateUrl: './rio-recipes.component.html',
  styleUrls: ['./rio-recipes.component.scss']
})
export class RioRecipesComponent implements OnInit {

  public recipes: Recipe[];
  constructor(public rioHttpClientService: RioHttpClientService) { }

  ngOnInit(): void {
   this.rioHttpClientService.fetchAllRecipes().subscribe(response => {
      this.recipes = response;
   });
  }

}
