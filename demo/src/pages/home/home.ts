import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { SingleSignOn } from '@teammaestro/capacitor-single-signon';

@Component({
    selector: 'page-home',
    templateUrl: 'home.html'
})
export class HomePage {
    constructor(
        public navCtrl: NavController,
        private singleSignOn: SingleSignOn
    ) { }

    loadUrl() {
        this.singleSignOn.authenticate({
            customScheme: 'myapp://',
            url: 'https://login.mywebsite.com/oauth'
        }).then(response => {
            console.log(`Completion URL : ${response.url}`);
        }).catch(error => {
             console.log(`Error : ${error}`);
        })
    }
}
