declare global {
    interface PluginRegistry {
        SingleSignOnPlugin?: ISingleSignOnPlugin;
    }
}

export interface ISingleSignOnPlugin {
    authenticate(
        options: {
            url: string;
            customScheme?: string;
        }
    ): Promise<{ url: string }>;
}
