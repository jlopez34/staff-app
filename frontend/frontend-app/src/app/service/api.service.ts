import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { PersonResponse } from '../model/person-response';
import { Person } from '../model/person.model';
import { Observable } from 'rxjs/index';
import { ApiResponse } from '../model/api.response';


@Injectable()
export class ApiService {


  constructor(private http: HttpClient) { }
  baseUrl: string = 'http://localhost:8080/staff/persons/';



  login(loginPayload): Observable<ApiResponse> {
    return this.http.post<ApiResponse>('http://localhost:8080/' + 'token/generate-token', loginPayload);
  }

  getPersons(): Observable<PersonResponse> {
    const headers = this.getUserPassword();
    return this.http.get<PersonResponse>(this.baseUrl, { headers });
  }

  getPersonById(id: string): Observable<Person> {
    const headers = this.getUserPassword();
    return this.http.get<Person>(this.baseUrl + id, { headers });
  }

  createPerson(person: Person): Observable<Person> {
    const headers = this.getUserPassword();
    return this.http.post<Person>(this.baseUrl, person, { headers });
  }

  updatePerson(person: Person): Observable<Person> {
    const headers = this.getUserPassword();
    return this.http.put<Person>(this.baseUrl + person.id, person, { headers });
  }

  deletePerson(id: string): Observable<Person> {
    const headers = this.getUserPassword();
    return this.http.delete<Person>(this.baseUrl + id, { headers });
  }

  getUserPassword(): HttpHeaders {
    let username = 'admin';
    let password = 'admin';
    return new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
  }
}
