import {Component, Input, OnInit} from '@angular/core';
import {RioRecipeService} from "../service/rio-recipe.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Recipe} from "../model/Recipe";
import {MatTableDataSource} from "@angular/material/table";
import {Ingredient} from "../model/Ingredient";

@Component({
  selector: 'app-rio-recipe',
  templateUrl: './rio-recipe.component.html',
  styleUrls: ['./rio-recipe.component.scss']
})
export class RioRecipeComponent implements OnInit {
  recipe: Recipe;
  loading: boolean = true;
  found: boolean = false;

  ingredientDataSource = new MatTableDataSource<Ingredient>();
  displayedColumns: string[] = ['name', 'quantity'];

  constructor(private rioRecipeService: RioRecipeService, private activatedRoute: ActivatedRoute,private router:Router) {
  }

  ngOnInit(): void {
    this.rioRecipeService.fetchById(this.activatedRoute.snapshot.paramMap.get('id')).toPromise().then(r => {
      this.recipe = r;
      this.ingredientDataSource.data = this.recipe.ingredients;
      this.found = true;
    }).catch(reason => {
      this.found = false;
    }).finally(() => this.loading = false);
  }

  toCreatePost() {
    this.router.navigate(['createPost'], { state: { recipe: this.recipe } });
  }
}
