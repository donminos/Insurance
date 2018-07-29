import { Component } from '@angular/core';
import { NavController, AlertController, Platform } from 'ionic-angular';
import { TranslateService } from '@ngx-translate/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Geolocation } from '@ionic-native/geolocation';
import { host } from '../../config';

@Component({
  selector: 'page-welcome',
  templateUrl: 'welcome.html'
})
export class WelcomePage {

  public userLogin: any = { items: [] };
  public browserLang: any;

  async characterLoad() {
    let db = new PouchDB('Insurance');
    db.get('1').then((doc) => {
      this.userLogin = doc;
    }).catch((err) => {
      console.log(err);
      return {};
    });
  }

  constructor(public navCtrl: NavController, translate: TranslateService, public alertCtrl: AlertController,
    public http: HttpClient, public platform: Platform, private geolocation: Geolocation) {
    this.characterLoad();
    // used to set the default language for multi language support
    //OPCIONAL SI SE DEJA CARGA MAS RAPIDO EL IDIOMA
    this.browserLang = translate.getBrowserLang();
    translate.use(this.browserLang.match(/en|es/) ? this.browserLang : 'es');


    platform.registerBackButtonAction(() => {

    });
  }

  signalHelp() {
    this.geolocation.getCurrentPosition({ enableHighAccuracy: true }).then((resp) => {
      // resp.coords.latitude
      // resp.coords.longitude
      let headers = new HttpHeaders({ "Content-Language": this.browserLang, "token": "1234564798" });
      this.http.post(host+'api/incidents/create.do', { "latitude": resp.coords.latitude, "longitude": resp.coords.longitude },
        { headers }
      ).subscribe(data => {   // data is already a JSON object
        debugger;
        let alert = this.alertCtrl.create({
          title: 'Low battery',
          subTitle: '10% of battery remaining',
          buttons: ['Dismiss']
        });
        alert.present();
      });

    }).catch((error) => {
      debugger;
      let alert = this.alertCtrl.create({
        title: 'Low battery',
        subTitle: 'Error getting location'+ error,
        buttons: ['Dismiss']
      });
      alert.present();
      console.log('Error getting location'+ error);
    });
  }


}
