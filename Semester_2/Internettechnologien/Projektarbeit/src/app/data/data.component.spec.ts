import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DataComponent } from './data.component';

// file from angular
describe('DataComponent', () => {
  let component: DataComponent;
  let fixture: ComponentFixture<DataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DataComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
