import { Component } from '@angular/core';
import { ControllerService } from "../controller.service";
import {NgIf} from "@angular/common";
import {ChatComponent} from "../chat/chat.component";
import {LoginComponent} from "../login/login.component";

@Component({
  selector: 'app-chatbot',
  standalone: true,
    imports: [
        NgIf,
        ChatComponent,
        LoginComponent
    ],
  templateUrl: './chatbot.component.html',
  styleUrl: './chatbot.component.css'
})
export class ChatbotComponent {
    constructor(protected controller: ControllerService) {
    }
}
