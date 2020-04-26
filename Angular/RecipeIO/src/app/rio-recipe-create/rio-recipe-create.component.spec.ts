import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RioRecipeCreateComponent } from './rio-recipe-create.component';

describe('RioRecipeCreateComponent', () => {
  let component: RioRecipeCreateComponent;
  let fixture: ComponentFixture<RioRecipeCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RioRecipeCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RioRecipeCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
