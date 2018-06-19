declare global {
  interface PluginRegistry {
    CustomTabsPlugin?: ICustomTabsPlugin;
  }
}

export interface ICustomTabsPlugin {
  show(
    options: {
      url: string;
      customScheme?: string;
    }
  ): Promise<{value: string}>;

  view(options: { url: string }): Promise<any>;
}
