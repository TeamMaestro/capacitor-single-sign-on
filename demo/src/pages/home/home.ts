import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { CustomTabs } from 'capacitor-custom-tabs';
@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {
  customTabs: CustomTabs;
  constructor(public navCtrl: NavController) {
    this.customTabs = new CustomTabs();
  }

  loadUrl() {
    this.customTabs.show(
      {
        url:
          'http://waithook.com/testing_422?forward=co.fitcom.customtabsdemo%3A%2F%2Ftest123'
      },
      (data, err) => {
        if (data) {
          console.log(`Data : ${data}`);
        } else {
          console.log(`Error : ${err}`);
        }
      }
    );
  }
}
