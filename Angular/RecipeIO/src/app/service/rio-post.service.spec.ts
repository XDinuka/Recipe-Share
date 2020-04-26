import { TestBed } from '@angular/core/testing';

import { RioPostService } from './rio-post.service';

describe('RioPostService', () => {
  let service: RioPostService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RioPostService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
