import { TestBed } from '@angular/core/testing';

import { OlympicService } from './olympic.service';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('OlympicService', () => {
  let service: OlympicService;

  beforeEach(() => {
    TestBed.configureTestingModule({
    imports: [],
    providers: [provideHttpClient(withInterceptorsFromDi())]
});
    service = TestBed.inject(OlympicService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
