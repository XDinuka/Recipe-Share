import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RioSettingsComponent } from './rio-settings.component';

describe('RioSettingsComponent', () => {
  let component: RioSettingsComponent;
  let fixture: ComponentFixture<RioSettingsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RioSettingsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RioSettingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
