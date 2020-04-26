import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatListModule} from '@angular/material/list';
import {MatSidenavModule} from '@angular/material/sidenav';
import { FlexLayoutModule } from '@angular/flex-layout';
import {MatMenuModule} from '@angular/material/menu';
import { RioRecipesComponent } from './rio-recipes/rio-recipes.component';
import { RioRecipeComponent } from './rio-recipe/rio-recipe.component';
import {HttpClientModule} from '@angular/common/http';
import {MatExpansionModule} from "@angular/material/expansion";
import {MatCardModule} from "@angular/material/card";
import {MatFormFieldModule} from '@angular/material/form-field';
import { RioIntroComponent } from './rio-intro/rio-intro.component';
import {RioHttpClientService} from "./service/rio-http-client.service";
import {RioUsersService} from "./service/rio-users.service";
import {MatInputModule} from "@angular/material/input";
import {MatTabsModule} from "@angular/material/tabs";
import { RioHomeComponent } from './rio-home/rio-home.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { RioNavbarComponent } from './rio-navbar/rio-navbar.component';
import { LayoutModule } from '@angular/cdk/layout';
import {MatCheckboxModule} from "@angular/material/checkbox";
import { RioRecipeCreateComponent } from './rio-recipe-create/rio-recipe-create.component';
import {MatDialogModule} from "@angular/material/dialog";
import {MatStepperModule} from "@angular/material/stepper";
import {MatButtonToggleModule} from "@angular/material/button-toggle";
import {MatChipsModule} from "@angular/material/chips";
import { RioIngredientTableComponent } from './rio-ingredient-table/rio-ingredient-table.component';
import {MatTableModule} from "@angular/material/table";
import {MatTreeModule} from "@angular/material/tree";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatProgressBarModule} from "@angular/material/progress-bar";
import { RioPostCreateComponent } from './rio-post-create/rio-post-create.component';
import {MatRadioModule} from "@angular/material/radio";
import { RioPostComponent } from './rio-post/rio-post.component';
import { RioPostViewComponent } from './rio-post-view/rio-post-view.component';
import { RioSettingsComponent } from './rio-settings/rio-settings.component';
import {MAT_SNACK_BAR_DEFAULT_OPTIONS, MatSnackBar} from "@angular/material/snack-bar";
import { RioCommentComponent } from './rio-comment/rio-comment.component';
import { RioRecipeCardComponent } from './rio-recipe-card/rio-recipe-card.component';
import { RioPostsComponent } from './rio-posts/rio-posts.component';
import {ScrollingModule} from "@angular/cdk/scrolling";


@NgModule({
  declarations: [
    AppComponent,
    RioRecipesComponent,
    RioRecipeComponent,
    RioIntroComponent,
    RioHomeComponent,
    RioNavbarComponent,
    RioRecipeCreateComponent,
    RioIngredientTableComponent,
    RioPostCreateComponent,
    RioPostComponent,
    RioPostViewComponent,
    RioSettingsComponent,
    RioCommentComponent,
    RioRecipeCardComponent,
    RioPostsComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        MatToolbarModule,
        MatIconModule,
        MatButtonModule,
        MatListModule,
        MatSidenavModule,
        FlexLayoutModule,
        MatMenuModule,
        HttpClientModule,
        MatExpansionModule,
        MatCardModule,
        MatInputModule,
        MatFormFieldModule,
        MatTabsModule,
        ReactiveFormsModule,
        LayoutModule,
        MatCheckboxModule,
        FormsModule,
        MatDialogModule,
        MatStepperModule,
        MatButtonToggleModule,
        MatChipsModule,
        MatTableModule,
        MatTreeModule,
        MatProgressSpinnerModule,
        MatProgressBarModule,
        MatRadioModule,
        ScrollingModule
    ],
  providers: [
    {provide: MAT_SNACK_BAR_DEFAULT_OPTIONS, useValue: {duration: 2500}},
    RioHttpClientService,
    RioUsersService,
    MatSnackBar
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
