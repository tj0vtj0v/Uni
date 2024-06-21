import {Component} from '@angular/core';
import {DataComponent} from "../data/data.component";

@Component({
    selector: 'app-login',
    standalone: true,
    imports: [
        DataComponent
    ],
    templateUrl: './login.component.html',
    styleUrl: './login.component.css'
})
// logic of the login component
export class LoginComponent {
    // yep there is none :)
}
