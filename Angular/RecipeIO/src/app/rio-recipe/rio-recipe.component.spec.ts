import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RioRecipeComponent } from './rio-recipe.component';

describe('RioRecipeComponent', () => {
  let component: RioRecipeComponent;
  let fixture: ComponentFixture<RioRecipeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RioRecipeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RioRecipeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
