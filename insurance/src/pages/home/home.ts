import { Component } from '@angular/core';
import { NavController, AlertController, LoadingController,Platform } from 'ionic-angular';
import { TranslateService } from '@ngx-translate/core';
import { WelcomePage } from '../welcome/welcome';
import { AdjusterPage } from '../adjuster/adjuster';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { host } from '../../config';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'

})
export class HomePage {
  private http: any;
  private browserLang: string;
  constructor(public navCtrl: NavController, public translate: TranslateService, http: HttpClient, public alertCtrl: AlertController, public loadingCtrl: LoadingController,public platform: Platform) {
    this.http = http;
    // used to set the default language for multi language support
    //OPCIONAL SI SE DEJA CARGA MAS RAPIDO EL IDIOMA
    this.browserLang = translate.getBrowserLang();
    translate.use(this.browserLang.match(/en|es/) ? this.browserLang : 'es');

    platform.registerBackButtonAction(() => {

    });
  }

  openWelcome(user: string, pass: string) {
    let loading;
    this.translate.get('PLEASE_WAIT').subscribe(
      value => {
        // value is our translated string
        loading = this.loadingCtrl.create({
          content: value,
          dismissOnPageChange: true
        });
        loading.present();
      }
    )
    let headers = new HttpHeaders({ "Content-Language": this.browserLang, "token": "1234564798" });
    //headers.append('token', '123456789');

    this.http.post(host + 'api/access/login.do', { "user": user, "password": pass },
      { headers }
    ).subscribe(data => {   // data is already a JSON object
      if (data.response.success) {
        let db = new PouchDB('Insurance');
        data.response.data._id = '1';
        data.response.data.image = 'assets/imgs/logo.png';
        db.put(
          data.response.data
            /*{
            _id: '1',
            name: 'Francisco Gabilondo Soler',
            quote: 'cantautor',
            image: 'assets/imgs/logo.png',
            items: [
              { title: 'Fecha expedición', note: '12/12/2017' },
              { title: 'Fecha vencimiento', note: '12/12/2018' },
              { title: 'Auto', note: 'Seat ibiza 2017' }
            ]
          }*/).then((response) => {
            if (data.response.data.role == 'ROLE_CLIENT') {
              this.navCtrl.push(WelcomePage);
            } else if (data.response.data.role == 'ROLE_ADJUSTER') {
              this.navCtrl.push(AdjusterPage);
            }
          }).catch((err) => {
            loading.dismiss();
            this.translate.get('ACCEPT').subscribe(accept => {
              this.translate.get('ERROR').subscribe(text => {
                let alert = this.alertCtrl.create({
                  title: text,
                  subTitle: data.response.error,
                  buttons: [accept]
                });
                alert.present();
              });
            });
            console.log(err);
          });
      } else {
        loading.dismiss();
        this.translate.get('ACCEPT').subscribe(accept => {
          this.translate.get('ERROR').subscribe(text => {
            let alert = this.alertCtrl.create({
              title: text,
              subTitle: data.response.error,
              buttons: [accept]
            });
            alert.present();
          });
        });
      }
    })/*.error((error: any) => {
      loading.dismiss();
      this.translate.get('ACCEPT').subscribe(accept => {
        this.translate.get('ERROR').subscribe(text => {
          let alert = this.alertCtrl.create({
            title: text,
            subTitle: "Error",
            buttons: [accept]
          });
          alert.present();
        });
      });
    });*/
  }

}
