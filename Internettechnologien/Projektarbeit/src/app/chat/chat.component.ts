import {Component} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgForOf} from "@angular/common";
import {ApiService} from "../api.service";

@Component({
    selector: 'app-chat',
    standalone: true,
    imports: [
        ReactiveFormsModule,
        FormsModule,
        NgForOf
    ],
    templateUrl: './chat.component.html',
    styleUrl: './chat.component.css'
})
export class ChatComponent {
    messages: string[][] = [];
    message: string = "";
    topic: string = "";

    constructor(private api: ApiService) {
        api.get_next_topic().subscribe(value => {this.topic = value as string});
    }

    sendMessage() {
        this.messages.push([this.message, "user-text"]);
        this.answerToMessage();
        this.message = "";
    }

    answerToMessage() {
        this.messages.push(["aaaaaaaaaaa", "bot-text"]);
        let result: string = "";
        this.api.get_result(this.topic, this.message).subscribe(value => {result = value as string})
    }
}
