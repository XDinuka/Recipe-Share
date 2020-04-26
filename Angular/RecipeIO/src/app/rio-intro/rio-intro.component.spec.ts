import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RioIntroComponent } from './rio-intro.component';

describe('RioIntroComponent', () => {
  let component: RioIntroComponent;
  let fixture: ComponentFixture<RioIntroComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RioIntroComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RioIntroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
