import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CopilotTestComponent } from './copilot-test.component';

describe('CopilotTestComponent', () => {
  let component: CopilotTestComponent;
  let fixture: ComponentFixture<CopilotTestComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CopilotTestComponent]
    });
    fixture = TestBed.createComponent(CopilotTestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
