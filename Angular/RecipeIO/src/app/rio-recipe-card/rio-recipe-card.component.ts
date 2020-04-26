import {Component, Input, OnInit} from '@angular/core';
import {Recipe} from "../model/Recipe";

@Component({
  selector: 'app-rio-recipe-card',
  templateUrl: './rio-recipe-card.component.html',
  styleUrls: ['./rio-recipe-card.component.scss']
})
export class RioRecipeCardComponent implements OnInit {

  @Input()
  recipe :Recipe;

  constructor() { }

  ngOnInit(): void {
  }

}
