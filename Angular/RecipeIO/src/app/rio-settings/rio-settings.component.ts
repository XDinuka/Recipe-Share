import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {RioUsersService} from "../service/rio-users.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-rio-settings',
  templateUrl: './rio-settings.component.html',
  styleUrls: ['./rio-settings.component.scss']
})
export class RioSettingsComponent implements OnInit {

  changePasswordForm: FormGroup;

  constructor(private rioUsersService: RioUsersService, private formBuilder: FormBuilder, private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.changePasswordForm = this.formBuilder.group({
      pwnew: ['', Validators.required],
      pwcnf: ['', Validators.required]
    });
  }

  changePassword() {
    if (this.changePasswordForm.controls['pwnew'].value === this.changePasswordForm.controls['pwcnf'].value) {
      this.rioUsersService.changePassword(this.changePasswordForm.controls['pwnew'].value)
        .toPromise().then(value => {
        // localStorage.setItem("username", value.username);
        // localStorage.setItem("id", value.id + "");
        localStorage.setItem("token", value.password);
        // this.router.navigate(['home']);
        this.openSnackBar('Password changed.');
      }).catch(reason => {

        this.openSnackBar('Failed to change password.');
      })
    }else{
      this.openSnackBar('Passwords don\'t match.');
    }
  }

  openSnackBar(message: string) {
    this.snackBar.open(message);

    // , action, {
    //     duration: 2000,
    //   }
  }
}
