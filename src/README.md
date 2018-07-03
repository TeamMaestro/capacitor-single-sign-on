# Capacitor Single SignOn

[![npm](https://img.shields.io/npm/v/capacitor-single-signon.svg)](https://www.npmjs.com/package/capacitor-single-signon)
[![npm](https://img.shields.io/npm/dt/capacitor-single-signon.svg?label=npm%20downloads)](https://www.npmjs.com/package/capacitor-single-signon)

## Installation

- `npm i @teammaestro/capacitor-single-signon`

## Usage

### iOS
This supports iOS SDK version 11+ (Important). The build will fail on SDK version 10 and below. Make sure to set your custom scheme in the `Info.plist`

### Android

Add the following to your Android.manifest

```xml
<activity
        android:name="net.openid.appauth.RedirectUriReceiverActivity"
        tools:node="replace">
    <intent-filter>
        <action android:name="android.intent.action.VIEW"/>
        <category android:name="android.intent.category.DEFAULT"/>
        <category android:name="android.intent.category.BROWSABLE"/>
        <data android:scheme="yourCustomScheme"/>
    </intent-filter>
</activity>
```

You will need to comment the default intent filter

```xml
<intent-filter>
                <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.BROWSABLE" />
                <data android:scheme="@string/custom_url_scheme" />
</intent-filter>
```

```ts
import { SingleSignOn } from '@teammaestro/capacitor-single-signon';
const sso = new SingleSignOn();

sso
  .authenticate({ url: 'someUrl', customScheme: 'customSchemeIfNeeded' })
  .then(response => {
    // this response will contain your completion URL with all your authorization keys used from the oauth callback
  })
  .catch(err => {});
```

## Api

| Method                                               | Default | Type                      | Description                 |
| ---------------------------------------------------- | ------- | ------------------------- | --------------------------- |
| authenticate(options: { url: string, customScheme: string }) |         | `Promise<{ url: string }>` | Can be used for sso/oauth |

