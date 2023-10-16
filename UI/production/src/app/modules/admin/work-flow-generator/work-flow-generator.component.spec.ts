import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkFlowGeneratorComponent } from './work-flow-generator.component';

describe('WorkFlowGeneratorComponent', () => {
  let component: WorkFlowGeneratorComponent;
  let fixture: ComponentFixture<WorkFlowGeneratorComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [WorkFlowGeneratorComponent]
    });
    fixture = TestBed.createComponent(WorkFlowGeneratorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
