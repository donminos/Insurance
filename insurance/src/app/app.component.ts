import { Component } from '@angular/core';
import { Platform, LoadingController } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { OneSignal, OSNotificationPayload } from '@ionic-native/onesignal';
import { isCordovaAvailable } from '../common/is-cordova-available';
import { oneSignalAppId, sender_id } from '../config';

import { TranslateService } from '@ngx-translate/core';
import { WelcomePage } from '../pages/welcome/welcome';
import { AdjusterPage } from '../pages/adjuster/adjuster';
import { MapsPage } from '../pages/maps/maps';
import { HomePage } from '../pages/home/home';
import { AssignedIncidentPage } from '../pages/assignedIncident/assignedIncident';


@Component({
  templateUrl: 'app.html'
})


export class MyApp {

  rootPage: any = HomePage;

  async afterLogin() {
    let db = new PouchDB('Insurance');
    db.get('1').then((doc) => {
      if (doc != null) {
        if (doc['role'] == 'ROLE_CLIENT') {
          this.rootPage = WelcomePage;
        } else {
          this.rootPage = AdjusterPage;
        }
      } else {
        this.rootPage = HomePage;
      }
    }).catch((err) => {
      console.log(err);
      this.rootPage = HomePage;
    });
  }

  constructor(platform: Platform, statusBar: StatusBar, splashScreen: SplashScreen, translate: TranslateService, public loadingCtrl: LoadingController, private oneSignal: OneSignal) {
    platform.ready().then(() => {
      // Okay, so the platform is ready and our plugins are available.
      // Here you can do any higher level native things you might need.
      this.afterLogin();

      translate.addLangs(["en", "es"]);
      translate.setDefaultLang('en');

      let browserLang = translate.getBrowserLang();
      translate.use(browserLang.match(/en|es/) ? browserLang : 'en');

      statusBar.styleLightContent();
      splashScreen.hide();

      if (isCordovaAvailable()) {
        this.oneSignal.startInit(oneSignalAppId, sender_id);
        this.oneSignal.inFocusDisplaying(this.oneSignal.OSInFocusDisplayOption.Notification);
        this.oneSignal.handleNotificationReceived().subscribe(data => this.onPushReceived(data.payload));
        this.oneSignal.handleNotificationOpened().subscribe(data => this.onPushOpened(data.notification.payload));
        this.oneSignal.endInit();
        this.oneSignal.sendTag("InternalUser", "PruebaUser");
      }
    });

  }
  private onPushReceived(payload: OSNotificationPayload) {
    alert('Push recevied:' + payload.body);
  }

  private onPushOpened(payload: OSNotificationPayload) {
    alert('Push opened: ' + payload.body);
  }
}

