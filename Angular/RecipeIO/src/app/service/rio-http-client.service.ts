import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RioHttpClientService {

  constructor(private httpClient: HttpClient) { }


  public fetchAllRecipes() {
    return this.httpClient.get<Recipe[]>('http://localhost:5001/recipe');
  }


  // public fetchAllRecipes(quection: Quection) {
  //   return this.httpClient.delete<Quection>('http://localhost:8080/emp/delete' + "/1" + quection.id);
  // }


}



export class Ingredient {
  id: number;
  name: string;
  isVegan: boolean;
}

export class RecipeIngredient {
  ingredientId: number;
  quantity: string;
}

export class Recipe {
  id: number;
  name: string;
  ingredients: RecipeIngredient[];
}

