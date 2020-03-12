import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RioNavbarComponent } from './rio-navbar.component';

describe('RioNavbarComponent', () => {
  let component: RioNavbarComponent;
  let fixture: ComponentFixture<RioNavbarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RioNavbarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RioNavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
