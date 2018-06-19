import { WebPlugin } from '@capacitor/core';
import { ICustomTabsPlugin } from './definitions';

export class CustomTabsPluginWeb extends WebPlugin implements ICustomTabsPlugin {
  view(options: { url: string; }): Promise<any> {
    return new Promise(()=>{
      console.log(options);
    });
  }
  show(options: { url: string; customScheme?: string; }): Promise<{value:string}>{
      return new Promise(()=>{
          console.log(options);
      });
  }
  constructor() {
    super({
      name: 'CustomTabsPlugin',
      platforms: ['web']
    });
  }

}

const CustomTabsPlugin = new CustomTabsPluginWeb();

export { CustomTabsPlugin };
