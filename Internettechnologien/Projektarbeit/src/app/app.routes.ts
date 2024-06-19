import {Routes} from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {ChatbotComponent} from "./chatbot/chatbot.component";
import {AboutComponent} from "./about/about.component";
import {ImprintComponent} from "./imprint/imprint.component";

export const routes: Routes = [
    {path: '', redirectTo: 'login', pathMatch: 'full'},
    {path: 'login', component: LoginComponent},
    {path: 'chat', component: ChatbotComponent},
    {path: 'about', component: AboutComponent},
    {path: 'imprint', component: ImprintComponent}
];
