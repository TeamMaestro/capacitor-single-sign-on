# Capacitor Single SignOn

[![npm](https://img.shields.io/npm/v/@teammaestro/capacitor-single-signon.svg)](https://www.npmjs.com/package/@teammaestro/capacitor-single-signon)
[![npm](https://img.shields.io/npm/dt/@teammaestro/capacitor-single-signon.svg?label=npm%20downloads)](https://www.npmjs.com/package/@teammaestro/capacitor-single-signon)

## Installation

- `npm i @teammaestro/capacitor-single-signon`

## Usage

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
  .show({ url: 'someUrl', customScheme: 'customSchemeIfNeeded' })
  .then(data => {
    // data contains the info used to start the app e.g data from fb callback
  })
  .catch(err => {});
```

## Api

| Method                                               | Default | Type                      | Description                 |
| ---------------------------------------------------- | ------- | ------------------------- | --------------------------- |
| show(options: { url: string, customScheme: string }) |         | `Promise<{value:string}>` | Can be used for sso/oauth |

