import {Injectable} from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class ControllerService {

    constructor() {
    }

    loggedIn: boolean = false;
    name: string | undefined;

    logIn(name: string) {
        this.name = name;
        this.loggedIn = true;
    }

    public getLoginName() {
        if (this.name) {
            return this.name;
        } else {
            return "";
        }
    }

    isLoggedIn() {
        return this.loggedIn;
    }
}
