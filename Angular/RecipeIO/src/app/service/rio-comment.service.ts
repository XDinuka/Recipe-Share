import { Injectable } from '@angular/core';
import {Post} from "../model/Post";
import {RioHttpClientService} from "./rio-http-client.service";
import {Comment} from "../model/Comment";

@Injectable({
  providedIn: 'root'
})
export class RioCommentService {

  constructor(private rioHttpClientService:RioHttpClientService) { }

  createComment(comment:Comment){
    return this.rioHttpClientService.post<Post>('comment',comment);
  }

  getAllByPostID(postID:any){
    return this.rioHttpClientService.get<Comment[]>('comment/post/'+postID);
  }
}
