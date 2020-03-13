import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RioNavbarComponent } from './rio-navbar/rio-navbar.component';
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

@NgModule({
  declarations: [
    AppComponent,
    RioNavbarComponent,
    RioRecipesComponent,
    RioRecipeComponent
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
    MatCardModule
  ],
  providers: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
