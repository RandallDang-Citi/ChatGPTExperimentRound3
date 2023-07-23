import { Component } from '@angular/core';
import { getMetaData } from './getMetaData';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'copilotPro';
  // set the metaData from getMetaData function
  metaData = getMetaData();
  _metaData = [];

  ngOnInit(): void {
    this._metaData = this.metaData.modal
  }

  submit(data: any) {
    console.log(data);
  }

}
