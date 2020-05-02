import {TestBed} from '@angular/core/testing';

import {PhoneService} from './phone.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('PhoneService', () => {
  let service: PhoneService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    service = TestBed.inject(PhoneService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
