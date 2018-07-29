import { Component } from '@angular/core';
import { NavController, AlertController, Platform } from 'ionic-angular';
import { TranslateService } from '@ngx-translate/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Geolocation } from '@ionic-native/geolocation';
import { BackgroundGeolocation, BackgroundGeolocationConfig, BackgroundGeolocationResponse } from '@ionic-native/background-geolocation';
import { HomePage } from '../home/home';
import { host } from '../../config';

@Component({
  selector: 'page-adjuster',
  templateUrl: 'adjuster.html'
})
export class AdjusterPage {
  public userLogin: any = { items: [] };
  private browserLang: any;

  async characterLoad() {
    let db = new PouchDB('Insurance');
    db.get('1').then((doc) => {
      this.userLogin = doc;
      this.launchBackground(doc);
    }).catch((err) => {
      console.log(err);
      return {};
    });
  }

  constructor(public navCtrl: NavController, translate: TranslateService, public alertCtrl: AlertController,
    public http: HttpClient, public platform: Platform, private geolocation: Geolocation,
    public backgroundGeolocation: BackgroundGeolocation) {
    this.characterLoad();
    // used to set the default language for multi language support
    //OPCIONAL SI SE DEJA CARGA MAS RAPIDO EL IDIOMA
    this.browserLang = translate.getBrowserLang();
    translate.use(this.browserLang.match(/en|es/) ? this.browserLang : 'es');
    
  }

  launchBackground(doc) {
    let config: BackgroundGeolocationConfig = {
      desiredAccuracy: 0,
      stationaryRadius: 20,
      distanceFilter: 20,
      //interval:1000,
      notificationTitle: 'Sicronizando',
      notificationText: 'Obteniendo y mandando su ubicación',
      url: host + 'api/insuranceAdjuster/registerLocation.do',
      httpHeaders:{"Content-Language": this.browserLang,"Token": doc.token},
      //debug: true, //  enable this hear sounds for background-geolocation life-cycle.
      stopOnTerminate: false, // enable this to clear background location settings when the app terminates
    };
    
    this.backgroundGeolocation.configure(config)
      .subscribe((location: BackgroundGeolocationResponse) => {
        //let headers = new HttpHeaders({ "Content-Language": this.browserLang, "Token": "a"/*this.userLogin.token*/ });
        //headers.append('token', '123456789');

        /*this.http.post(host + 'api/insuranceAdjuster/registerLocation.do', {"latitude":location.latitude.toString(),"longitude":location.longitude.toString()},
          { headers }
        ).subscribe(data => {   // data is already a JSON object*/
          console.log(location);

          // IMPORTANT:  You must execute the finish method here to inform the native plugin that you're finished,
          // and the background-task may be completed.  You must do this regardless if your HTTP request is successful or not.
          // IF YOU DON'T, ios will CRASH YOUR APP for spending too much time in the background.
          //this.backgroundGeolocation.finish(); // FOR IOS ONLY
        //});
      });
    this.backgroundGeolocation.start();
  }
  logout(){
    this.backgroundGeolocation.stop();
    let db = new PouchDB('Insurance');
    db.destroy().then((doc) => {
      this.navCtrl.push(HomePage);
    }).catch((err) => {
      console.log(err);
      this.navCtrl.push(HomePage);
    });    
  }
}
