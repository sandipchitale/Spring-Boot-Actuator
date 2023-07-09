import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

// import { BreadcrumbModule } from 'primeng/breadcrumb';
import { ButtonModule } from 'primeng/button';
import { CheckboxModule } from 'primeng/checkbox';
// import { DialogModule } from 'primeng/dialog';
import { DropdownModule } from 'primeng/dropdown';
// import { InputSwitchModule } from 'primeng/inputswitch';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { InputTextModule } from 'primeng/inputtext';

// import { ListboxModule } from 'primeng/listbox';
import { MenuModule } from 'primeng/menu';
// import { OverlayPanelModule } from 'primeng/overlaypanel';
// import { SelectButtonModule } from 'primeng/selectbutton';
// import { SplitButtonModule } from 'primeng/splitbutton';
import { TableModule } from 'primeng/table';
import { TabViewModule } from 'primeng/tabview';
import { ToggleButtonModule } from 'primeng/togglebutton';
import { ToolbarModule } from 'primeng/toolbar';
// import { TooltipModule } from 'primeng/tooltip';
// import { TreeTableModule } from 'primeng/treetable';

// NG Translate
import { SplitButtonModule } from 'primeng/splitbutton';

import { AppComponent } from './app.component';

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    CommonModule,
    FormsModule,
    // BreadcrumbModule,
    ButtonModule,
    CheckboxModule,
    // DialogModule,
    DropdownModule,
    // InputSwitchModule,
    InputTextareaModule,
    InputTextModule,
    // ListboxModule,
    MenuModule,
    // OverlayPanelModule,
    // SelectButtonModule,
    SplitButtonModule,
    TableModule,
    TabViewModule,
    ToggleButtonModule,
    ToolbarModule,
    // TooltipModule,
    // TreeTableModule
    // SplitButtonModule,
  ],
  providers: [
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
