declare global {
    interface PluginRegistry {
        SingleSignOn?: SingleSignOn;
    }
}

export interface SingleSignOn {
    authenticate(
        options: {
            url: string;
            customScheme?: string;
        }
    ): Promise<{ url: string }>;
}
