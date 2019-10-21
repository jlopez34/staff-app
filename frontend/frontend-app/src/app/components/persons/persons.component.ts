import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Person } from "../../model/person.model";
import { ApiService } from "../../service/api.service";

@Component({
  selector: 'app-persons',
  templateUrl: './persons.component.html',
  styleUrls: ['./persons.component.css']
})
export class PersonsComponent implements OnInit {

  persons: Person[];

  constructor(private router: Router, private apiService: ApiService) { }

  ngOnInit() {
    /*if (!window.localStorage.getItem('token')) {
      this.router.navigate(['login']);
      return;
    }*/
    this.apiService.getPersons().subscribe(
      data => {
        this.persons = data._embedded.personList;
      });
  }

  deletePerson(person: Person): void {
    this.apiService.deletePerson(person.id).subscribe(
      data => {
        this.persons = this.persons.filter(p => p !== person)
      }
    );
  }

  editPerson(person: Person): void {
    window.localStorage.removeItem("editPersonId");
    window.localStorage.setItem("editPersonId", person.id);
    this.router.navigate(['edit-person']);
  }

  addPerson(): void {
    this.router.navigate(['add-person'])
  }

}
