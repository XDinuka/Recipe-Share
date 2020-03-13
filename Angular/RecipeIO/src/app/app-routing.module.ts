import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {RioRecipesComponent} from './rio-recipes/rio-recipes.component';


const routes: Routes = [
  {
    path: 'recipes',
    component: RioRecipesComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
