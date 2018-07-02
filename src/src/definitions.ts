declare global {
  interface PluginRegistry {
    SingleSignOnPlugin?: ISingleSignOnPlugin;
  }
}

export interface ISingleSignOnPlugin {
  show(
    options: {
      url: string;
      customScheme?: string;
    }
  ): Promise<{value: string}>;
}
