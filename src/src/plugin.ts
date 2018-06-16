import { Plugins } from '@capacitor/core';
import { ICustomTabsPlugin } from './definitions';
const { CustomTabsPlugin } = Plugins;

export class CustomTabs implements ICustomTabsPlugin {
  show(
    options: {
      url: string;
      customScheme?: string;
    },
    response: Function
  ): void {
    CustomTabsPlugin.show(options, response);
  }
}
