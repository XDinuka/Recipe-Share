import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RioPostComponent } from './rio-post.component';

describe('RioPostComponent', () => {
  let component: RioPostComponent;
  let fixture: ComponentFixture<RioPostComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RioPostComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RioPostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
