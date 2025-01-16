import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { UniversiteListComponent } from './components/universite-list/universite-list.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    UniversiteListComponent
  ],
  imports: [
    BrowserModule,
    FormsModule, // Add FormsModule here
    HttpClientModule // Add HttpClientModule if needed
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
