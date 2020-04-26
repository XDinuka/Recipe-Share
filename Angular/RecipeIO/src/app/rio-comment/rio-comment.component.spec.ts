import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RioCommentComponent } from './rio-comment.component';

describe('RioCommentComponent', () => {
  let component: RioCommentComponent;
  let fixture: ComponentFixture<RioCommentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RioCommentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RioCommentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
