import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RioRecipeCardComponent } from './rio-recipe-card.component';

describe('RioRecipeCardComponent', () => {
  let component: RioRecipeCardComponent;
  let fixture: ComponentFixture<RioRecipeCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RioRecipeCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RioRecipeCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
