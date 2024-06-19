import { Component } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RouterLink} from "@angular/router";
import {ControllerService} from "../controller.service";

@Component({
  selector: 'app-data',
  standalone: true,
    imports: [
        ReactiveFormsModule,
        RouterLink,
        FormsModule
    ],
  templateUrl: './data.component.html',
  styleUrl: './data.component.css'
})
export class DataComponent {
    constructor(protected controller: ControllerService) {}
    username: string = "";

    confirm() {
        this.controller.logIn(this.username)
    }
}
