import { Plugins } from '@capacitor/core';
import { ICustomTabsPlugin } from './definitions';
const { CustomTabsPlugin } = Plugins;

export class CustomTabs implements ICustomTabsPlugin {
  show(
    options: {
      url: string;
      customScheme?: string;
    }
  ):Promise<{value:string}>  {
    return CustomTabsPlugin.show(options);
  }

  view(options: { url: string }): Promise<any> {
    return CustomTabsPlugin.view(options);
  }
}
