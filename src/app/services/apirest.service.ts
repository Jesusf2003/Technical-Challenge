import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApirestService {

  private API_SERVER = environment.api;

  constructor(private http: HttpClient) {}

  public conversion(
    from: string,
    to: string,
    amount: number
  ): Observable<any> {
    return this.http.get<any>(this.API_SERVER + '/convert' + '?from='+from+'&to='+to+'&amount='+amount);
  }
}
