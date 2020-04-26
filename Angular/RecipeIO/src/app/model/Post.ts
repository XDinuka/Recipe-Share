import {Recipe} from "./Recipe";
import {User} from "./User";

export interface Post {
  id: number;
  recipe: Recipe;
  text: string;
  image: string;
  user:User;
}
