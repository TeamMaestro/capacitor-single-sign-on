import { WebPlugin } from '@capacitor/core';
import { ISingleSignOnPlugin } from './definitions';

export class SingleSignOnPluginWeb extends WebPlugin implements ISingleSignOnPlugin {
  show(options: { url: string; customScheme?: string; }): Promise<{value:string}>{
      return new Promise(()=>{
          console.log(options);
      });
  }
  constructor() {
    super({
      name: 'SingleSignOnPlugin',
      platforms: ['web']
    });
  }

}

const SingleSignOnPlugin = new SingleSignOnPluginWeb();

export { SingleSignOnPlugin };
