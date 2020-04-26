import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RioIngredientTableComponent } from './rio-ingredient-table.component';

describe('RioIngredientTableComponent', () => {
  let component: RioIngredientTableComponent;
  let fixture: ComponentFixture<RioIngredientTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RioIngredientTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RioIngredientTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
