import { TestBed } from '@angular/core/testing';

import { RioCommentService } from './rio-comment.service';

describe('RioCommentService', () => {
  let service: RioCommentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RioCommentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
