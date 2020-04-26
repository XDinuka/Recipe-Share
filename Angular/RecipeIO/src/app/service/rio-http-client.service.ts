import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RioHttpClientService {

  private serverLink: string = 'http://localhost:5000/';

  constructor(private httpClient: HttpClient) {
  }

  private getHttpHeaders():HttpHeaders {
    let httpHeaders = new HttpHeaders();//.set('Access-Control-Allow-Origin', '*');
    if (localStorage.getItem("token") == null)
      return httpHeaders;
    return httpHeaders
      .set("Authorization", 'Bearer ' + localStorage.getItem("token"));
      // .set('Authorization2','Bearer ' + localStorage.getItem("token"));
  }


  public get<T>(url: string): Observable<T> {
    return this.httpClient.get<T>(
      this.serverLink + url,
      {
        headers: this.getHttpHeaders()
      }
    )
  }
  public delete<T>(url: string): Observable<T> {
    return this.httpClient.delete<T>(
      this.serverLink + url,
      {
        headers: this.getHttpHeaders()
      }
    )
  }

  public post<T>(url: string, body: any): Observable<T> {
    return this.httpClient.post<T>(
      this.serverLink + url,
      body,
      {
        headers: this.getHttpHeaders()
      }
    )
  }


  // private getHttpHeaders(){
  //   return new HttpHeaders()
  //     .set('Access-Control-Allow-Origin', '*')
  //     .set("Authorization", localStorage.getItem("token"));
  // }
  //
  //
  // public fetchAllRecipes() {
  //   return this.httpClient.get<Recipe[]>(this.serverLink + '/recipe', {
  //     headers: this.getHttpHeaders()
  //   });
  // }
  //
  // public deleteRecipe() {
  //   // this.httpClient
  //   //TODO add code
  // }
  //
  // public createOrUpdateRecipe(recipe: Recipe) {
  //   //TODO add code
  // }
  //
  //
  // // public fetchAllRecipes(quection: Quection) {
  // //   return this.httpClient.delete<Quection>('http://localhost:8080/emp/delete' + "/1" + quection.id);
  // // }


}


// export class Ingredient {
//   id: number;
//   name: string;
//   isVegan: boolean;
// }
//
// export class RecipeIngredient {
//   ingredientId: number;
//   quantity: string;
// }
//
// export class Recipe {
//   id: number;
//   name: string;
//   ingredients: RecipeIngredient[];
// }

