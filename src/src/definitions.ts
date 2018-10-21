declare global {
    interface PluginRegistry {
        SingleSignOn?: SingleSignOnPlugin;
    }
}

export interface SingleSignOnPlugin {
    authenticate(
        options: {
            url: string;
            customScheme?: string;
        }
    ): Promise<{ url: string }>;
}
