import { Component } from '@angular/core';

import { Plugins } from '@capacitor/core';
const { SingleSignOn } = Plugins;

@Component({
    selector: 'app-home',
    templateUrl: 'home.page.html',
    styleUrls: ['home.page.scss'],
})
export class HomePage {

    async sso() {
        try {
            const ssoResponse = await SingleSignOn.authenticate({
                customScheme: 'myapp://',
                url: 'https://login.mywebsite.com/oauth'
            });
            console.log(`Completion URL: ${ssoResponse.url}`);
        } catch (error) {
            console.error(`Error: ${error}`);
        }
    }

}
