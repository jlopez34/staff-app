import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonsAddComponent } from './persons-add.component';

describe('PersonsAddComponent', () => {
  let component: PersonsAddComponent;
  let fixture: ComponentFixture<PersonsAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PersonsAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PersonsAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
