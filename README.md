# Capacitor Custom Tabs

[![npm](https://img.shields.io/npm/v/capacitor-custom-tabs.svg)](https://www.npmjs.com/package/capacitor-custom-tabs)
[![npm](https://img.shields.io/npm/dt/capacitor-custom-tabs.svg?label=npm%20downloads)](https://www.npmjs.com/package/capacitor-custom-tabs)
[![Build Status](https://travis-ci.org/triniwiz/capacitor-custom-tabs.svg?branch=master)](https://travis-ci.org/triniwiz/capacitor-custom-tabs)

## Installation

- `npm i capacitor-custom-tabs`

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
import { CustomTabs } from 'capacitor-custom-tabs';
const customTabs = new CustomTabs();

customTabs
  .show({ url: 'someUrl', customScheme: 'customSchemeIfNeeded' })
  .then(data => {
    // data contains the info used to start the app e.g data from fb callback
  })
  .catch(err => {});

customTabs
  .view({ url: 'someExternalUrl' })
  .then()
  .catch();
```

## Api

| Method                                               | Default | Type                      | Description                 |
| ---------------------------------------------------- | ------- | ------------------------- | --------------------------- |
| show(options: { url: string, customScheme: string }) |         | `Promise<{value:string}>` | Can be used for sso/oauth   |
| view(options: { url: string })                       |         | `Promise<any>`            | Can be used to view any url |

## Example Image

| IOS         | Android     |
| ----------- | ----------- |
| Coming Soon | Coming Soon |
