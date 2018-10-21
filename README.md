# Capacitor Single SignOn

[![npm](https://img.shields.io/npm/v/@teammaestro/capacitor-single-signon.svg)](https://www.npmjs.com/package/teammaestro/capacitor-single-signon)
[![npm](https://img.shields.io/npm/dt/@teammaestro/capacitor-single-signon.svg?label=npm%20downloads)](https://www.npmjs.com/package/teammaestro/capacitor-single-signon)

## Installation

- `npm i @teammaestro/capacitor-single-signon`

## Usage

### iOS
This supports iOS SDK version 11+ (Important). The build will fail on SDK version 10 and below. Set the iOS `minVersion` in your `capacitor.config.json` to at least `11.0`. Also, make sure to set your custom scheme in the `Info.plist`.

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
import '@teammaestro/capacitor-single-signon';

import { Plugins } from '@capacitor/core';
const { SingleSignOn } = Plugins;

try {
    const response = await SingleSignOn.authenticate({
        url: 'someUrl',
        customScheme: 'customSchemeIfNeeded'
    });
    // this response will contain your completion URL with all your authorization keys used from the oauth callback
    console.log(response.url);
} catch (error) {
    console.error(error);
}
```

## Api

| Method                                               | Default | Type                      | Description                 |
| ---------------------------------------------------- | ------- | ------------------------- | --------------------------- |
| authenticate(options: { url: string, customScheme: string }) |         | `Promise<{ url: string }>` | Can be used for sso/oauth |

