import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RioPostViewComponent } from './rio-post-view.component';

describe('RioPostViewComponent', () => {
  let component: RioPostViewComponent;
  let fixture: ComponentFixture<RioPostViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RioPostViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RioPostViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
