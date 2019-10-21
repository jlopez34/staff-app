import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { PersonsComponent } from './components/persons/persons.component';
import { PersonsAddComponent } from './components/persons-add/persons-add.component';
import { PersonsEditComponent } from './components/persons-edit/persons-edit.component';
import { LoginComponent } from './components/login/login.component';
import { routing } from "./app.routing";
import { ReactiveFormsModule } from "@angular/forms";
import { ApiService } from "./service/api.service";
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { TokenInterceptor } from "./core/interceptor";

@NgModule({
  declarations: [
    AppComponent,
    PersonsComponent,
    PersonsAddComponent,
    PersonsEditComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    routing,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [ApiService, {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
