import {Component} from '@angular/core';
import {ApiService} from "../api.service";
import {ControllerService} from "../controller.service";
import {NgForOf, NgIf, NgOptimizedImage} from "@angular/common";
import {ChatComponent} from "../chat/chat.component";
import {ChatbotComponent} from "../chatbot/chatbot.component";

@Component({
    selector: 'app-evaluation',
    standalone: true,
    imports: [
        NgForOf,
        NgIf,
        ChatComponent,
        ChatbotComponent,
        NgOptimizedImage
    ],
    templateUrl: './evaluation.component.html',
    styleUrl: './evaluation.component.css'
})
export class EvaluationComponent {
    constructor(private api: ApiService, private controller: ControllerService) {
    }

    evaluation_data: any[][] = []
    name?: string

    ngOnInit() {
        this.api.get_evaluation(this.controller.sessionID).subscribe(data => this.evaluation_data = data as any)
        this.name = this.controller.name
    }

    protected readonly console = console;
}
