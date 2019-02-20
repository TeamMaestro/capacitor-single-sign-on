# Capacitor Single SignOn

[![npm](https://img.shields.io/npm/v/@teamhive/capacitor-single-signon.svg)](https://www.npmjs.com/package/teamhive/capacitor-single-signon)
[![npm](https://img.shields.io/npm/dt/@teamhive/capacitor-single-signon.svg?label=npm%20downloads)](https://www.npmjs.com/package/teamhive/capacitor-single-signon)

## Installation

- `npm i @teamhive/capacitor-single-signon`

## Usage

### iOS
This supports iOS SDK version 11+ (Important). The build will fail on SDK version 10 and below. Set the iOS `minVersion` in your `capacitor.config.json` to at least `11.0`. Also, make sure to set your custom scheme in the `Info.plist`.

### Android

Make sure you have the `launchMode` on the `MainActivity` set to `singleTask`:

```xml
<activity
    android:configChanges="orientation|keyboardHidden|keyboard|screenSize|locale"
    android:launchMode="singleTask"
    android:name="com.my.app"
    android:label="@string/title_activity_main"
    android:theme="@style/AppTheme.NoActionBarLaunch">
```

```ts
import '@teamhive/capacitor-single-signon';

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

