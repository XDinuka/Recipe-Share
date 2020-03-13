import {Component, Input, OnInit} from '@angular/core';
import {Recipe, RioHttpClientService} from '../service/rio-http-client.service';

@Component({
  selector: 'app-rio-recipe',
  templateUrl: './rio-recipe.component.html',
  styleUrls: ['./rio-recipe.component.scss']
})
export class RioRecipeComponent implements OnInit {
  @Input()
  recipe: Recipe;
  constructor() { }

  ngOnInit(): void {
  }

}
