import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {RioRecipesComponent} from './rio-recipes/rio-recipes.component';
import {RioIntroComponent} from "./rio-intro/rio-intro.component";
import {RioHomeComponent} from "./rio-home/rio-home.component";
import {RioRecipeCreateComponent} from "./rio-recipe-create/rio-recipe-create.component";
import {RioRecipeComponent} from "./rio-recipe/rio-recipe.component";
import {RioPostCreateComponent} from "./rio-post-create/rio-post-create.component";
import {RioPostViewComponent} from "./rio-post-view/rio-post-view.component";
import {RioSettingsComponent} from "./rio-settings/rio-settings.component";
import {RioPostsComponent} from "./rio-posts/rio-posts.component";


const routes: Routes = [
  {
    path: 'recipes',
    component: RioRecipesComponent
  },{
    path: 'recipe/:id',
    component: RioRecipeComponent
  },{
    path: 'post/:id',
    component: RioPostViewComponent
  }, {
    path: 'intro',
    component: RioIntroComponent
  }, {
    path: 'settings',
    component: RioSettingsComponent
  },{
    path: 'posts',
    component: RioPostsComponent
  },{
    path: 'home',
    component: RioHomeComponent
  },{
    path: 'createRecipe',
    component: RioRecipeCreateComponent
  },{
    path: 'createPost',
    component: RioPostCreateComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
