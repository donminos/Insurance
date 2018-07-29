import { Component } from '@angular/core';
import { NavController, AlertController, Platform } from 'ionic-angular';
import { TranslateService } from '@ngx-translate/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Geolocation, Geoposition } from '@ionic-native/geolocation';
import { host } from '../../config';

@Component({
  selector: 'page-assigned',
  templateUrl: 'assignedIncident.html'
})
export class AssignedIncidentPage {

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
    //this.characterLoad();
    // used to set the default language for multi language support
    //OPCIONAL SI SE DEJA CARGA MAS RAPIDO EL IDIOMA
    this.browserLang = translate.getBrowserLang();
    translate.use(this.browserLang.match(/en|es/) ? this.browserLang : 'es');
  }
  openMap() {
    this.geolocation.getCurrentPosition()
      .then(position => {
        if (this.platform.is('ios')) {
          //window.open('maps://?q=' + offer.position.lat + ',' + offer.position.lng, '_system');
        }
        if (this.platform.is('android')) {
          window.open('geo://' + position.coords.latitude + ',' + position.coords.longitude + '?q=' + 19.2031996 + ',' + -99.0511676 + '(INCIDENTE)', '_system');
        }
      })
      .catch(error => {
        console.log(error);
      })
  }

}
