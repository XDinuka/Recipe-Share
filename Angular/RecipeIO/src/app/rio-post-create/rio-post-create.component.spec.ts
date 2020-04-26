import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RioPostCreateComponent } from './rio-post-create.component';

describe('RioPostCreateComponent', () => {
  let component: RioPostCreateComponent;
  let fixture: ComponentFixture<RioPostCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RioPostCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RioPostCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
