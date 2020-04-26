import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RioPostsComponent } from './rio-posts.component';

describe('RioPostsComponent', () => {
  let component: RioPostsComponent;
  let fixture: ComponentFixture<RioPostsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RioPostsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RioPostsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
