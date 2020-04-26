import { Component } from '@angular/core';
import {RioUsersService} from "./service/rio-users.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'RecipeIO';

  constructor(private rioUsersService :RioUsersService,private router: Router,private activatedRoute: ActivatedRoute){
    this.rioUsersService.check().subscribe(ok=>{
      if(this.router.url==='/'){
        this.router.navigate(['home']);
      }

    },er=>{
      localStorage.removeItem('token');
      localStorage.removeItem('username');
      localStorage.removeItem('id');
      this.router.navigate(['intro']);
    });




  }




}
