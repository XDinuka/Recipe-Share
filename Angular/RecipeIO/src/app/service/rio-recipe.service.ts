import {Injectable} from '@angular/core';
import {RioHttpClientService} from "./rio-http-client.service";
import {StringMessage} from "../model/StringMessage";
import {Recipe} from "../model/Recipe";

@Injectable({
  providedIn: 'root'
})
export class RioRecipeService {

  constructor(private rioHttpClientService: RioHttpClientService) {
  }


  createRecipe(recipe: Recipe) {
    return this.rioHttpClientService.post<Recipe>('recipe/', recipe);
  }

  fetchById(id: string) {
    return this.rioHttpClientService.get<Recipe>('recipe/' + id);
  }

  fetchByName(name: string) {
    return this.rioHttpClientService.post<Recipe[]>('recipe/search/name', {value: name});
  }
  fetchByNameVegan(name: string) {
    return this.rioHttpClientService.post<Recipe[]>('recipe/search/name/vegan', {value: name});
  }
}
