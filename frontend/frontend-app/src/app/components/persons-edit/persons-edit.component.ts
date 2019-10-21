import { Component, OnInit, Inject } from '@angular/core';
import { Router } from "@angular/router";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { first } from "rxjs/operators";
import { Person } from "../../model/person.model";
import { ApiService } from "../../service/api.service";

@Component({
  selector: 'app-persons-edit',
  templateUrl: './persons-edit.component.html',
  styleUrls: ['./persons-edit.component.css']
})
export class PersonsEditComponent implements OnInit {

  person: Person;
  editForm: FormGroup;
  constructor(private formBuilder: FormBuilder, private router: Router, private apiService: ApiService) { }

  ngOnInit() {
    let personId = window.localStorage.getItem("editPersonId");
    if (!personId) {
      this.router.navigate(['list-person']);
      return;
    }
    this.editForm = this.formBuilder.group({
      id: [''],
      nome: ['', Validators.required],
      email: ['', Validators.required],
      sexo: ['', Validators.required],
      naturalidade: ['', Validators.required],
      nacionalidade: ['', Validators.required]
    });
    this.apiService.getPersonById(personId)
      .subscribe(data => {
        const personUpdate = {
          id: data.id,
          nome: data.nome,
          email: data.email,
          sexo: data.sexo,
          naturalidade: data.naturalidade,
          nacionalidade: data.nacionalidade
        };

        this.editForm.setValue(personUpdate);
      });

  }

  onSubmit() {
    this.apiService.updatePerson(this.editForm.value)
      .pipe(first())
      .subscribe(
        data => {
          if (data) {
            alert('User updated successfully.');
            this.router.navigate(['list-person']);
          } else {
            alert('Error updated');
          }
        },
        error => {
          alert(error);
        });
  }

}


