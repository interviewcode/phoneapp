import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {PhoneService} from '../phone.service';
import {ErrorStateMatcher} from '@angular/material/core';
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {AlphanumericResults, EmptyAlphanumericResult} from '../interfaces/alphanumeric-results';
import {PageEvent} from '@angular/material/paginator';
import {appConfiguration} from '../app.constants';

@Component({
  selector: 'app-validator',
  templateUrl: './phonenumber.component.html',
  styleUrls: ['./phonenumber.component.scss']
})
export class PhonenumberComponent implements OnInit {

  matcher = new PhoneNumberErrorStateMatcher();
  results: AlphanumericResults = EmptyAlphanumericResult;
  pageSize = appConfiguration.pageSize;

  private validPhoneNumber = '';


  phoneFormControl = new FormControl('', [
    Validators.required,
    Validators.maxLength(14),
    Validators.minLength(7),
    // Should match pattern on backend.
    Validators.pattern('(\\(?[0-9A-Za-z]{3}\\)?[- ]?)?[0-9A-Za-z]{3}[- ]?[0-9A-Za-z]{4}')
  ]);


  constructor(private phoneService: PhoneService) {
  }

  ngOnInit(): void {
  }

  onKey(_: KeyboardEvent) {
    if (!this.matcher.isErrorState(this.phoneFormControl, null)) {
      this.validPhoneNumber = this.phoneFormControl.value;
      this.requestData(null);
    } else {
      this.validPhoneNumber = null;
      this.results = EmptyAlphanumericResult;
    }
  }

  handlePageChange(pageEvent: PageEvent) {
    this.requestData(pageEvent);
  }

  private handleDataChange(result: AlphanumericResults) {
    this.results = result;
  }

  private requestData(pageInfo: PageEvent | null) {
    this.phoneService.getAlphanumerics(this.validPhoneNumber, pageInfo)
      .toPromise()
      .then((result: AlphanumericResults) => this.handleDataChange(result));
  }


}

export class PhoneNumberErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    return !!(control && control.invalid && (control.dirty || control.touched));
  }
}
