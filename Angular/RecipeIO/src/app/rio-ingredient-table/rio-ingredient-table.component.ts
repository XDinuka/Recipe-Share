import {Component, Input, OnInit} from '@angular/core';
import {Ingredient} from "../model/Ingredient";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-rio-ingredient-table',
  templateUrl: './rio-ingredient-table.component.html',
  styleUrls: ['./rio-ingredient-table.component.scss']
})
export class RioIngredientTableComponent implements OnInit {
  @Input()
  ingredients : Ingredient[];


  dataSource = new MatTableDataSource<Ingredient>();

  displayedColumns: string[] = ['name', 'quantity'];
  constructor() { }

  ngOnInit(): void {
    this.dataSource.data = this.ingredients;

  }

  update(ingredients: Ingredient[]) {
    this.dataSource.data = ingredients;
  }
}
