import { TestBed } from '@angular/core/testing';

import { RioHttpClientService } from './rio-http-client.service';

describe('RioHttpClientService', () => {
  let service: RioHttpClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RioHttpClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
