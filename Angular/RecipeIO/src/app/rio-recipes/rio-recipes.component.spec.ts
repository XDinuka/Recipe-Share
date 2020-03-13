import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RioRecipesComponent } from './rio-recipes.component';

describe('RioRecipesComponent', () => {
  let component: RioRecipesComponent;
  let fixture: ComponentFixture<RioRecipesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RioRecipesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RioRecipesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
