import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root',
})

export class userService {
    private apiUrl = 'http://localhost:8081/api/auth';

    constructor(private http: HttpClient){}

    login(email: string , password: string): Observable<any> {
         const body = {email , password};
         return this.http.post(`${this.apiUrl}/login`, body);
    }
}
