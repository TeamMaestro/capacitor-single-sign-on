export interface SingleSignOnPlugin {
    authenticate(
        options: {
            url: string;
            customScheme?: string;
        }
    ): Promise<{ url: string }>;
}
