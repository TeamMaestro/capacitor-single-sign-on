# @teamhive/capacitor-single-signon

This plugin is designed to streamline the single signon process between android and ios

## Install

```bash
npm install @teamhive/capacitor-single-signon
npx cap sync
```

## API

<docgen-index>

* [`authenticate(...)`](#authenticate)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### authenticate(...)

```typescript
authenticate(options: AuthenticateOptions): Promise<AuthenticateResponse>
```

| Interface | Type |
| --- | --- |
| **`AuthenticateOptions`** | <code>{ url: string; customScheme?: string; prefersEphemeralWebBrowserSession?: boolean }</code> |
| **`AuthenticateResponse`** | <code>{ url: string; }</code> |

</docgen-api>
