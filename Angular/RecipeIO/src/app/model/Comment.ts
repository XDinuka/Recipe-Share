import {User} from "./User";

export interface Comment {
  id:number;
  postID:number;
  userID:number;
  text:string;
  user:User;
}

