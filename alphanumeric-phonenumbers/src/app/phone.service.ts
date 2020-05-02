import {Injectable} from '@angular/core';

import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {catchError, tap} from 'rxjs/operators';
import {AlphanumericResults} from './interfaces/alphanumeric-results';
import {PageEvent} from '@angular/material/paginator';
import {appConfiguration} from './app.constants';


@Injectable({
  providedIn: 'root'
})
export class PhoneService {

  private phoneResourceUrl = appConfiguration.baseUrl + '/phonenumbers';


  constructor(private http: HttpClient) {

  }

  //Used as initial integration (interactive) with the backend.
  validateNumber(phoneNumberToValidate: string): Observable<boolean> {

    const validationUrl = this.phoneResourceUrl + '/' + phoneNumberToValidate + '/validation';

    return this.http.get<boolean>(validationUrl)
      .pipe(
        tap(result => console.log('fetched' + JSON.stringify(result)),
          catchError(this.handleError<boolean>('validateNumber', false))
        ));
  }

  getAlphanumerics(phoneNumber: string, pageInfo: PageEvent | null): Observable<AlphanumericResults> {

    const alphaNumericUrl = this.phoneResourceUrl + '/' + phoneNumber + '/alphanumerics';

    const params = {
      pageNumber: ((pageInfo && pageInfo.pageIndex) || 0).toString(),
      pageSize: ((pageInfo && pageInfo.pageSize) || appConfiguration.pageSize).toString()
    };


    return this.http.get<AlphanumericResults>(alphaNumericUrl, {params})
      .pipe(
        tap(result => console.log('fetched' + JSON.stringify(result)),
          catchError(this.handleError<boolean>('alphanumerics', false))
        ));

  }


  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any) => {
      console.error(`${operation} failed: ${error.message}`);
      return of(false);
    };
  }
}
