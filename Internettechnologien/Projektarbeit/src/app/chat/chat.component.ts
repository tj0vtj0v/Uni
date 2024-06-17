import {Component} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgForOf} from "@angular/common";
import {ApiService} from "../api.service";
import {take} from "rxjs";
import {resolve} from "@angular/compiler-cli";
import {log} from "@angular-devkit/build-angular/src/builders/ssr-dev-server";

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
    topic!: string;

    constructor(private api: ApiService) {
        this.topic = this.getNextTopic()
        this.addQuestion(this.topic)
    }

    sendMessage() {
        this.messages.push([this.message, "user-text"]);
        this.answerToMessage();
        this.message = "";
    }

    answerToMessage() {
        let result!: string;
        this.api.get_result(this.topic, this.message).subscribe(value => {
            result = value as string
        })


        this.topic = this.getNextTopic()
        this.addQuestion(this.topic)
    }

    getNextTopic():string {
        let a!: string;
        this.api.get_next_topic().pipe(take(1)).subscribe(value => {
            a = value as string
        })
        console.log("a", a)
        return "type"
    }

    addQuestion(topic: string) {
        this.api.get_question(topic).pipe(take(1)).subscribe(value => {
            this.messages.push([value as string, "bot-text"])
        })
    }
}
