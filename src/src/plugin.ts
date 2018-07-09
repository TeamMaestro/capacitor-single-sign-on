import { Plugins } from '@capacitor/core';
import { ISingleSignOnPlugin } from './definitions';

const {SingleSignOnPlugin} = Plugins;

export class SingleSignOn implements ISingleSignOnPlugin {
    authenticate(
        options: {
            url: string;
            customScheme?: string;
        }
    ): Promise<{ value: string }> {
        return SingleSignOnPlugin.authenticate(options);
    }
}
