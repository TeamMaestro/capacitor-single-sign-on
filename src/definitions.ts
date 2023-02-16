interface AuthenticateOptions {
    url: string;
    customScheme?: string;
    prefersEphemeralWebBrowserSession?: boolean;
}

interface AuthenticateResponse {
    url: string;
}

export interface SingleSignOnPlugin {
    authenticate(options: AuthenticateOptions): Promise<AuthenticateResponse>;
}
