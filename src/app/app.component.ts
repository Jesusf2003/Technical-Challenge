import { Component } from '@angular/core';
import { ApirestService } from './services/apirest.service';
import { ApiResponse } from './model/ApiResponse.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  from!: string;
  to!: string;
  amount!: number;

  public response: ApiResponse = new ApiResponse();

  constructor(
    private restService: ApirestService
  ) {}

  currencyCurrent() {
    this.restService.conversion(this.from, this.to, this.amount).subscribe(
      rest => {
        this.response = rest;
        console.log(this.response);
      }
    );
  }
}
