export class Message {
    constructor(text: string, sender: string, time: string) {
        this.text = text;
        this.time = time;
        this.sender = sender;
        this.class = sender === "Bot" ? "bot-text" : "user-text";
    }

    text: string;
    sender: string;
    time: string;
    class: string;
}
