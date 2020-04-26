import {Injectable} from '@angular/core';
import {RioHttpClientService} from "./rio-http-client.service";
import {AbstractControl, ValidationErrors} from "@angular/forms";
import {Observable, of} from "rxjs";
import {catchError, map} from "rxjs/operators";
import {StringMessage} from "../model/StringMessage";
import {User} from "../model/User";

@Injectable({
  providedIn: 'root'
})
export class RioUsersService {

  constructor(private rioHttpClientService: RioHttpClientService) {
  }

  check() {
    return this.rioHttpClientService.get<StringMessage>('auth/check');
  }

  login(username: string, password: string) {
    return this.rioHttpClientService.post<User>('auth/login', {
      username: username,
      password: password
    });
  }

  changePassword( password: string) {
    let user = new class implements User{
      id:number = parseInt(localStorage.getItem('id'));
      username: string = localStorage.getItem('username');
      password:string = password;
    }
    return this.rioHttpClientService.post<User>('auth/password', user);
  }

  signUp(username: string, password: string) {
    return this.rioHttpClientService.post<User>('auth/register', {
      username: username,
      password: password
    });
  }

  isUsernameUnique(ctrl: AbstractControl): Observable<ValidationErrors | null> {
    return this.rioHttpClientService.get<boolean>('auth/username/' + ctrl.value,
    ).pipe(
      map(isUnique => (isUnique ? null : {username: ctrl.value, isUnique: false})),
      catchError(() => of(null))
    );
  }

  fetchUsernameById(id: string) {
    return this.rioHttpClientService.get<StringMessage>('auth/id/' + id);
  }

}
