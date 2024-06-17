import { Component } from '@angular/core';
import {ControllerService} from "../controller.service";
import {FormsModule} from "@angular/forms";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-login',
  standalone: true,
    imports: [
        FormsModule,
        RouterLink
    ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
    constructor(protected controller: ControllerService) {}
    username: string = "Test User";

    confirm() {
        this.controller.logIn(this.username)
    }

}
