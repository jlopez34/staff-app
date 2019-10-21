

import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {PersonsAddComponent} from "./components/persons-add/persons-add.component";
import {PersonsComponent} from "./components/persons/persons.component";
import {PersonsEditComponent} from "./components/persons-edit/persons-edit.component";

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'add-person', component: PersonsAddComponent },
  { path: 'list-person', component: PersonsComponent },
  { path: 'edit-person', component: PersonsEditComponent },
  {path : '', component : LoginComponent}
];

export const routing = RouterModule.forRoot(routes);

