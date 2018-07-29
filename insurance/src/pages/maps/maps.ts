import { Component } from '@angular/core';
import { NavController, AlertController, Platform } from 'ionic-angular';
import { TranslateService } from '@ngx-translate/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Geolocation, Geoposition } from '@ionic-native/geolocation';
import { host } from '../../config';

declare var google;

@Component({
  selector: 'page-maps',
  templateUrl: 'maps.html'
})
export class MapsPage {
  map: any;

  public userLogin: any = { items: [] };
  public browserLang: any;
  public timeArrive:string="0";
  public distanceArrive:string="0";

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

    

    platform.registerBackButtonAction(() => {

    });
  }
  ionViewDidLoad() {//Carga una vez la patalla esta totlamente cargada
    this.getPosition();
  }

  getPosition(): any {
    this.geolocation.getCurrentPosition()
      .then(response => {
        this.loadMap(response);
      })
      .catch(error => {
        console.log(error);
      });
  }


  loadMap(position: Geoposition) {
    let latitude = position.coords.latitude;
    let longitude = position.coords.longitude;

    var directionsDisplay = new google.maps.DirectionsRenderer;
    var directionsService = new google.maps.DirectionsService;

    // create a new map by passing HTMLElement
    let mapEle: HTMLElement = document.getElementById('map');

    // create LatLng object
    let myLatLng = { lat: latitude, lng: longitude };

    // create map
    this.map = new google.maps.Map(mapEle, {
      center: myLatLng,
      zoom: 15,
      disableDefaultUI: true,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    directionsDisplay.setMap(this.map);

    this.calculateAndDisplayRoute(directionsService, directionsDisplay, myLatLng);// Mostar ruta
    this.moverRute(directionsService, directionsDisplay);
    mapEle.classList.add('show-map');
  }

  moverRute(directionsService, directionsDisplay) {

    setInterval(() => {
      this.geolocation.getCurrentPosition()
      .then(position => {
        let myLatLng = { lat: position.coords.latitude, lng: position.coords.longitude };
        this.calculateAndDisplayRoute(directionsService, directionsDisplay, myLatLng);// Mostar ruta
      })
      .catch(error => {
        console.log(error);
      })
      
    }, 10000);
  }
  calculateAndDisplayRoute(directionsService, directionsDisplay, myLatLng) {
    directionsService.route({
      origin: { lat: parseFloat(myLatLng.lat), lng: parseFloat(myLatLng.lng) },
      destination: { lat: 19.2031996, lng: -99.0511676 },
      travelMode: google.maps.TravelMode['DRIVING'],
      drivingOptions: {
        departureTime: new Date(Date.now()),  // for the time N milliseconds from now.
        trafficModel: 'pessimistic'
      }
    }, (response, status) => {
      if (status == 'OK') {
        this.timeArrive = response.routes[0].legs[0].duration_in_traffic.text;
        this.distanceArrive = response.routes[0].legs[0].distance.text;
        directionsDisplay.setDirections(response);
      } else {
        window.alert('Directions request failed due to ' + status);
      }
    });
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
