import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {BASE_URL} from "./app.component";

@Injectable({
    providedIn: 'root'
})
export class ApiService {

    constructor(private http: HttpClient) {
    }

    get_next_topic() {
        return this.http.get(`${BASE_URL}/next_question`)
    }

    get_question(topic: string) {
        return this.http.get(`${BASE_URL}/question/${topic}`)
    }

}
