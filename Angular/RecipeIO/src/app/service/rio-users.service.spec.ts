import { TestBed } from '@angular/core/testing';

import { RioUsersService } from './rio-users.service';

describe('RioUsersService', () => {
  let service: RioUsersService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RioUsersService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
