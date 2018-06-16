import { WebPlugin } from '@capacitor/core';
import { ICustomTabsPlugin } from './definitions';

export class CustomTabsPluginWeb extends WebPlugin implements ICustomTabsPlugin {
  view(options: { url: string; }): Promise<any> {
    return new Promise(()=>{
      console.log(options);
    });
  }
  show(options: { url: string; customScheme?: string; },response?: Function): void{
    console.log(options);
    console.log(response)
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
