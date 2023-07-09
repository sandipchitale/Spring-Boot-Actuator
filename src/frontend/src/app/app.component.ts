import { AfterViewInit, Component, Inject, OnInit } from '@angular/core';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, AfterViewInit {
  lightTheme = true;

  springbootAppUrl = 'http://localhost:8080/actuator';

  constructor(@Inject(DOCUMENT) private document: Document) {
  }

  ngOnInit(): void {

  }

  ngAfterViewInit(): void {
    this.adjustTheme();
  }

  adjustTheme(event?: any) {
    let theme = 'light-theme';
    if (this.lightTheme) {
      theme = 'light-theme';
    } else {
      theme = 'dark-theme';
    }
    const themeLink = this.document.getElementById('app-theme') as HTMLLinkElement;
    if (themeLink) {
        themeLink.href = theme + '.css';
    }
  }

}
