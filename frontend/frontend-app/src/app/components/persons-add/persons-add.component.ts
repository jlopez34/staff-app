import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { ApiService } from "../../service/api.service";

@Component({
  selector: 'app-persons-add',
  templateUrl: './persons-add.component.html',
  styleUrls: ['./persons-add.component.css']
})
export class PersonsAddComponent implements OnInit {

  constructor(private formBuilder: FormBuilder, private router: Router, private apiService: ApiService) { }

  addForm: FormGroup;

  ngOnInit() {
    this.addForm = this.formBuilder.group({
      id: [],
      nome: ['',Validators.required],
      sexo: [],
      email: ['',Validators.required],
      dataNacimento: [],
      naturalidade: [],
      nacionalidade: [],
      cpf: ['', Validators.required]
    });
  }

  onSubmit() {
    this.apiService.createPerson(this.addForm.value)
      .subscribe(data => {
        this.router.navigate(['list-person']);
      });
  }

}
