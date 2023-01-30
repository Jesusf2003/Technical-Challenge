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
  selectedOrigin!: string;
  selectedFate!: string;

  public response: ApiResponse = new ApiResponse();

  constructor(
    private restService: ApirestService
  ) {}

  origin = [
    { name: "USD" },
    { name: "PEN" },
    { name: "EUR" }
  ]

  fate = [
    { name: "USD" },
    { name: "PEN" },
    { name: "EUR" }
  ]

  updateOrigin(e: any) {
    this.selectedOrigin = e.target.value
    console.log(this.selectedOrigin);
  }

  updateFate(e: any) {
    this.selectedFate = e.target.value;
    console.log(this.selectedFate);
  }

  currencyCurrent() {
    this.restService.conversion(this.selectedOrigin, this.selectedFate, this.amount).subscribe(
      rest => {
        this.response = rest;
        console.log(this.response);
      }
    );
  }
}
