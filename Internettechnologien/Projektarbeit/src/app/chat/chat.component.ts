import {Component, ElementRef, ViewChild} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgForOf} from "@angular/common";
import {ApiService} from "../api.service";
import {ControllerService} from "../controller.service";
import {Message} from "../Message";
import {delay, take, timeout} from "rxjs";

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
    @ViewChild('messageContainer') private messageContainer!: ElementRef<HTMLDivElement>;
    messages: Message[] = [];
    message!: string;

    constructor(private api: ApiService, private controller: ControllerService) {
    }

    ngAfterViewChecked() {
        this.scroll_down()
    }

    ngOnInit() {
        this.api.greeting().pipe(take(1)).subscribe(text =>
            this.api.get_time().pipe(take(1)).subscribe(time =>
                this.messages.push(new Message(text as string, "Bot", time as string))
            )
        )
        this.scroll_down()
    }


    scroll_down() {
        try {
            this.messageContainer.nativeElement.scrollTop = this.messageContainer.nativeElement.scrollHeight;
        } catch (err) {
        }
    }

    sendMessage() {
        if (this.message != "") {
            this.api.get_time().subscribe(time => {
                    this.pushMessage(this.message, this.controller.name, time as string, 100);
                }
            )
            this.answerToMessage();
        }
    }

    answerToMessage() {
        this.api.get_time().pipe(take(1)).subscribe(time =>
            this.api.compute_input(this.controller.sessionID, this.message).pipe(take(1)).subscribe(response => {
                    this.message = "";
                    this.pushMessage(response as string, "Bot", time as string, 1000)
                }
            )
        )
    }

    async pushMessage(text: string, sender: string, time: string, wait: number) {
        await new Promise(_ => setTimeout(_, wait))
        this.messages.push(new Message(text, sender, time))
    }
}
