import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AlphanumericResults, EmptyAlphanumericResult} from '../interfaces/alphanumeric-results';
import {PageEvent} from '@angular/material/paginator';
import {appConfiguration} from '../app.constants';

/**
 * A dumb component that renders only what it's told.
 * Delegates all control to the parent.
 */
@Component({
  selector: 'app-phonelist',
  templateUrl: './phonelist.component.html',
  styleUrls: ['./phonelist.component.scss']
})
export class PhonelistComponent implements OnInit {

  @Output() handlePage: EventEmitter<PageEvent> = new EventEmitter<PageEvent>();
  @Input() pageSize: number;
  @Input() results: AlphanumericResults = EmptyAlphanumericResult;
  pageSizeOptions: number[] = [this.pageSize || appConfiguration.pageSize];


  constructor() {
  }

  ngOnInit(): void {
  }

  changePageRequest(event: PageEvent): void {
    this.handlePage.emit(event);
  }

  formatNumber(input: string): string {
    if (input.length === 7) {
      return input.substring(0, 3) + ' ' + input.substring(3, 7);
    }
    return input.substring(0, 3) + ' ' + input.substring(3, 6) + ' ' +
      input.substring(6, 10);
  }

}
