import {Component, Input} from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import {RioUsersService} from "../service/rio-users.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-rio-navbar',
  templateUrl: './rio-navbar.component.html',
  styleUrls: ['./rio-navbar.component.scss']
})
export class RioNavbarComponent {

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  @Input()
  pageTitle:string;

  constructor(private breakpointObserver: BreakpointObserver,private rioUsersService:RioUsersService,private router: Router) {}

  signOut() {
    localStorage.removeItem('token');
    localStorage.removeItem('username');
    localStorage.removeItem('id');
    this.router.navigate(['intro']);
  }
}
