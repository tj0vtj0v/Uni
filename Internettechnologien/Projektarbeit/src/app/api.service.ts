import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

const BASE_URL = 'http://127.0.0.1:8000';

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

    get_result(topic: string, input: string) {
        return this.http.get(`${BASE_URL}/result/${topic}/${input}`)
    }

}
