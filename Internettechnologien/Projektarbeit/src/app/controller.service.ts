import {Injectable} from '@angular/core';
import {ApiService} from "./api.service";

@Injectable({
    providedIn: 'root'
})
export class ControllerService {

    constructor(private api: ApiService) {
    }

    loggedIn: boolean = false;
    name!: string;
    sessionID!: number;

    logIn(name: string) {
        this.name = name;
        this.loggedIn = true;
        this.api.get_sid().subscribe(result => this.sessionID = result as number);
    }
}
