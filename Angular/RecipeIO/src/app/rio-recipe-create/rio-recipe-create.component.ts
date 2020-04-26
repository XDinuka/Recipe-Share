import {Component, OnInit, ViewChild} from '@angular/core';
import {Recipe} from "../model/Recipe";
import {Ingredient} from "../model/Ingredient";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatTableDataSource} from "@angular/material/table";
import {User} from "../model/User";
import {RioRecipeService} from "../service/rio-recipe.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-rio-recipe-create',
  templateUrl: './rio-recipe-create.component.html',
  styleUrls: ['./rio-recipe-create.component.scss']
})
export class RioRecipeCreateComponent implements OnInit {

  recipe: Recipe;

  recipeNameGroup: FormGroup;
  ingredientGroup: FormGroup;
  recipeInstructionsGroup: FormGroup;

  ingredientDataSource = new MatTableDataSource<Ingredient>();
  displayedColumns: string[] = ['name', 'quantity', 'remove'];
  displayedColumnsReview: string[] = ['name', 'quantity'];

  @ViewChild('stepper') stepper;


  // @ViewChild(RioIngredientTableComponent, { static: false }) rioIngredientTableComponent: RioIngredientTableComponent;


  constructor(private formBuilder: FormBuilder, private rioRecipeService: RioRecipeService, private router: Router) {
    this.recipe = new class implements Recipe {
      id: number;
      ingredients: Ingredient[] = [];
      name: string;
      vegan: boolean = false;
      instructions: string;
      user:User= new class implements User {
        id: number = parseInt(localStorage.getItem('id'));
        password: string = null;
        username: string = localStorage.getItem('username');
      };
    }

  }

  ngOnInit(): void {

    this.recipeNameGroup = this.formBuilder.group({
      recipeName: ['', Validators.required]
    });

    this.ingredientGroup = this.formBuilder.group({
      ingredientName: ['', Validators.required],
      ingredientQuantity: ['', Validators.required]
    });
    this.recipeInstructionsGroup = this.formBuilder.group({
      recipeInstructions: ['', Validators.required]
    });
  }

  submitRecipeName() {
    this.recipe.name = this.recipeNameGroup.controls['recipeName'].value;
  }

  submitRecipeInstructions() {
    this.recipe.instructions = this.recipeInstructionsGroup.controls['recipeInstructions'].value;
  }

  submitIngredient() {
    const ingredientName = this.ingredientGroup.controls['ingredientName'].value;
    const ingredientQuantity = this.ingredientGroup.controls['ingredientQuantity'].value;
    let found = false;
    let ingredient: Ingredient;
    for (ingredient of this.recipe.ingredients) {
      if (ingredient.name === ingredientName) {
        found = true;
        break;
      }
    }
    if (found) {
      // console.log(ingredient);
      this.remove(ingredient);
      // console.log('found');
    }
    this.recipe.ingredients.push(new class implements Ingredient {
      name: string = ingredientName;
      quantity: string = ingredientQuantity;
    });

    // this.ingredientGroup.controls['ingredientName'].clearValidators();
    // this.ingredientGroup.controls['ingredientQuantity'].clearValidators();
    this.ingredientGroup.reset();
    // this.ingredientGroup.markAsUntouched();
    // this.ingredientGroup.markAsPristine();

    // this.rioIngredientTableComponent.update(this.recipe.ingredients);
    this.updateIngredientTable();
    // if(this.displayedColumns.length==2)
    //   this.displayedColumns.push('remove');
  }

  // onNoClick(): void {
  //   this.dialogRef.close();
  // }

  // pr() {
  //   console.log(this.recipe);
  // }


  // visible = true;
  // selectable = true;
  // removable = true;
  // addOnBlur = true;
  // readonly separatorKeysCodes: number[] = [ENTER, COMMA];


  // add(event: MatChipInputEvent): void {
  //   const input = event.input;
  //   const value = event.value;
  //
  //   // Add our fruit
  //   if ((value || '').trim()) {
  //     this.recipe.ingredients.push({name: value.trim(), quantity: '0'});
  //   }
  //
  //   // Reset the input value
  //   if (input) {
  //     input.value = '';
  //   }
  // }

  remove(ingredient: Ingredient): void {
    const index = this.recipe.ingredients.indexOf(ingredient);
// console.log(index);
    if (index >= 0) {
      this.recipe.ingredients.splice(index, 1);
    }

    this.updateIngredientTable();
  }

  updateIngredientTable() {
    this.ingredientDataSource.data = this.recipe.ingredients;
  }

  submitRecipe() {
    const recipe = this.recipe;

    this.rioRecipeService.createRecipe(recipe).subscribe(r => {
      this.router.navigate(['recipe', r.id])
    });


    // this.stepper.reset();
  }


}
