import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RioHomeComponent } from './rio-home.component';

describe('RioHomeComponent', () => {
  let component: RioHomeComponent;
  let fixture: ComponentFixture<RioHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RioHomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RioHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
