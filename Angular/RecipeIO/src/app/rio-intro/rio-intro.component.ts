import {ChangeDetectionStrategy, Component, OnInit, SimpleChanges} from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  FormGroupDirective,
  NgForm,
  ValidationErrors,
  Validators
} from "@angular/forms";
import {RioUsersService} from "../service/rio-users.service";
import {ErrorStateMatcher} from "@angular/material/core";
import {Router} from "@angular/router";

@Component({
  selector: 'app-rio-intro',
  templateUrl: './rio-intro.component.html',
  styleUrls: ['./rio-intro.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class RioIntroComponent implements OnInit {

  signInFrom: FormGroup;

  signUpForm: FormGroup;

  signUpSubmitted: boolean = false;
  signInSubmitted: boolean = false;

  submittedInvalid: boolean = false;

  // matchErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
  //   return control.dirty && form.invalid;
  // }

  errorStateMatcher: ErrorStateMatcher;


  constructor(
    private formBuilder: FormBuilder,
    private rioUsersService: RioUsersService,
    private router: Router) {
    this.errorStateMatcher = new ConfirmPasswordErrorStateMatcher();
  }

  ngOnInit(): void {
    this.signInFrom = this.formBuilder.group(
      {
        'in-username': [null, Validators.required],
        'in-password': [null, Validators.required]
      }
    )
    this.signUpForm = this.formBuilder.group({
        'up-username': [null, Validators.required, this.rioUsersService.isUsernameUnique.bind(this.rioUsersService)],
        'up-password': [null, Validators.required],
        'up-password-c': [null, Validators.required],
      },
      {validators: this.checkPasswordMatch}
    );

  }


  submitSignIn() {
    if (!this.signInSubmitted) {
      this.signInSubmitted = true;
      this.rioUsersService.login(this.signInFrom.get('in-username').value, this.signInFrom.get('in-password').value).subscribe(value => {
        localStorage.setItem("username", value.username);
        localStorage.setItem("id", value.id + "");
        localStorage.setItem("token", value.password);
        this.router.navigate(['home']);
      }, error => {
        this.signInFrom.get('in-password').setValue(null);
        this.signInSubmitted = false;
        this.submittedInvalid = true;
      })
    }
  }

  submitSignUp() {
    if (!this.signUpSubmitted) {
      this.signUpSubmitted = true;
      this.rioUsersService.signUp(this.signUpForm.get('up-username').value, this.signUpForm.get('up-password').value).subscribe(
        value => {
          localStorage.setItem("username", value.username);
          localStorage.setItem("id", value.id + "");
          localStorage.setItem("token", value.password);
          this.router.navigate(['home']);
        }, error => {
          this.signUpSubmitted = false;
        })
    }

  }

  checkPasswordMatch(formGroup: FormGroup): ValidationErrors | null {
    const pw1 = formGroup.get('up-password');
    const pw2 = formGroup.get('up-password-c');

    return pw1 && pw2 && pw1.value === pw2.value ? null : {'noMatch': true};

  }

  getErrorMessage(inpName: string) {
    switch (inpName) {
      case 'up-username': {
        if (this.signUpForm.controls['up-username'].hasError('required')) {
          return "Username is required.";
        }
        return "Username is taken.";
      }
      case 'up-password': {
        return "Password is required.";
      }
      case 'up-password-c': {
        if (this.signUpForm.controls['up-password-c'].hasError('required')) {
          return "Password is required.";
        }
        return 'Passwords do not match.';
      }
      case 'in-password': {
        if (this.submittedInvalid) {

          this.submittedInvalid = false;
          return 'Username or Password is invalid.';
        }
        return 'Password is required.';
      }
    }
  }


}

class ConfirmPasswordErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {

    return control.dirty && (control.invalid || (form.invalid && form.hasError('noMatch')));
  }
}
