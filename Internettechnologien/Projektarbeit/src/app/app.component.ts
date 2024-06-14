import { Component } from '@angular/core';
import {RouterLink, RouterOutlet} from '@angular/router';

export const BASE_URL = 'http://127.0.0.1:8000';

@Component({
  selector: 'app-root',
  standalone: true,
    imports: [RouterOutlet, RouterLink],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Projektarbeit';
}
