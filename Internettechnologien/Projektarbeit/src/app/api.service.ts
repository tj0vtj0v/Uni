import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {take} from "rxjs";

const BASE_URL = 'http://127.0.0.1:8000';

@Injectable({
    providedIn: 'root'
})
export class ApiService {

    constructor(private http: HttpClient) {
    }

    greeting() {
        return this.http.get(`${BASE_URL}/`).pipe(take(1))
    }

    get_sid() {
        return this.http.get(`${BASE_URL}/sid`).pipe(take(1))
    }

    get_time() {
        return this.http.get(`${BASE_URL}/time`).pipe(take(1))
    }

    compute_input(sid: number, text: string) {
        return this.http.get(`${BASE_URL}/compute/${sid}/${text}`).pipe(take(1))
    }

    get_evaluation(sid:number) {
        return this.http.get(`${BASE_URL}/evaluation/${sid}`).pipe(take(1))
    }
}
