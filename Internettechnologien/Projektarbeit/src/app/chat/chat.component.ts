import {Component} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgForOf} from "@angular/common";
import {ApiService} from "../api.service";
import {ControllerService} from "../controller.service";
import {Message} from "../Message";
import {take} from "rxjs";

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
    messages: Message[] = [];
    message!: string;
    topic!: string;

    constructor(private api: ApiService, private controller: ControllerService) {
    }

    ngOnInit() {
        this.api.greeting().pipe(take(1)).subscribe(text =>
            this.api.get_time().pipe(take(1)).subscribe(time =>
                this.messages.push(new Message(text as string, "Bot", time as string))
            )
        )
    }

    sendMessage() {
        if (this.message != "") {
            this.api.get_time().subscribe(time => {
                    this.messages.push(new Message(this.message, this.controller.name, time as string));
                }
            )
            this.answerToMessage();
        }
    }

    answerToMessage() {
        this.api.get_time().pipe(take(1)).subscribe(time =>
            this.api.compute_input(this.controller.sessionID, this.message).pipe(take(1)).subscribe(response => {
                    this.messages.push(new Message(response as string, "Bot", time as string))
                    this.message = "";
                }
            )
        )
    }
}
