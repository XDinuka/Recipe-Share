import { Injectable } from '@angular/core';
import {RioHttpClientService} from "./rio-http-client.service";
import {Post} from "../model/Post";
import {StringMessage} from "../model/StringMessage";

@Injectable({
  providedIn: 'root'
})
export class RioPostService {

  constructor(private rioHttpClientService:RioHttpClientService) { }

  createPost(post:Post){
    return this.rioHttpClientService.post<Post>('post',post);
  }

  getAll(){
    return this.rioHttpClientService.get<Post[]>('post');
  }

  getByID(id:any){
    return this.rioHttpClientService.get<Post>('post/'+id);
  }
  deleteByID(id:any){
    return this.rioHttpClientService.delete<StringMessage>('post/delete/'+id);
  }

  getByUser(id:any){
    return this.rioHttpClientService.get<Post[]>('post/user/'+id);
  }

  getByRecipe(id:any){
    return this.rioHttpClientService.get<Post>('post/recipe/'+id);
  }

}
