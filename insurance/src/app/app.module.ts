import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';
import { OneSignal } from '@ionic-native/onesignal';
import { Geolocation } from '@ionic-native/geolocation';

import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';
import { WelcomePage } from '../pages/welcome/welcome';
import { MapsPage } from '../pages/maps/maps';
import { AssignedIncidentPage } from '../pages/assignedIncident/assignedIncident';
import { AdjusterPage } from '../pages/adjuster/adjuster';

import { TranslateModule, TranslateLoader } from "@ngx-translate/core";
import { TranslateHttpLoader } from "@ngx-translate/http-loader";
import { HttpClient, HttpClientModule } from "@angular/common/http"; 
import { BackgroundGeolocation } from '@ionic-native/background-geolocation';
import {enableProdMode} from '@angular/core';


export function createTranslateLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

enableProdMode();
@NgModule({
  declarations: [
    MyApp,
    HomePage,
    WelcomePage,
    AdjusterPage,
    MapsPage,
    AssignedIncidentPage
  ],
  imports: [
    HttpClientModule,
        TranslateModule.forRoot({
            loader: {
                provide: TranslateLoader,
                useFactory: createTranslateLoader,
                deps: [HttpClient]
            }
        }),
    BrowserModule,
    IonicModule.forRoot(MyApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage,
    WelcomePage,
    AdjusterPage,
    MapsPage,
    AssignedIncidentPage
  ],
  providers: [
    StatusBar,
    OneSignal,
    Geolocation,
    SplashScreen,,
    BackgroundGeolocation,
    {provide: ErrorHandler, useClass: IonicErrorHandler}
  ]
})
export class AppModule {}
