import { TestBed } from '@angular/core/testing';

import { RioRecipeService } from './rio-recipe.service';

describe('RioRecipeService', () => {
  let service: RioRecipeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RioRecipeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
