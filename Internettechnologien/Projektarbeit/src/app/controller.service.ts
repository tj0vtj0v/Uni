import {Injectable} from '@angular/core';
import {ApiService} from "./api.service";

@Injectable({
    providedIn: 'root'
})
// service for session management
export class ControllerService {

    // creates the session manager
    constructor(private api: ApiService) {
    }

    // instance variables
    loggedIn: boolean = false;
    name!: string;
    sessionID!: number;

    // logs user in and assigns session id
    logIn(name: string) {
        this.name = name;
        this.loggedIn = true;
        this.api.get_sid().subscribe(result => this.sessionID = result as number);
    }
}
