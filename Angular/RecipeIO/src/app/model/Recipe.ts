import {Ingredient} from "./Ingredient";
import {User} from "./User";

export interface Recipe {
  id:number;
  name: string;
  vegan: boolean;
  ingredients: Ingredient[];
  instructions: string;
  user :User;
}
