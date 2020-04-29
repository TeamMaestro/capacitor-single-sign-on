package com.teamhive.capacitor.plugin;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.PluginRequestCodes;

import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import androidx.browser.customtabs.CustomTabsSession;

@NativePlugin(requestCodes={PluginRequestCodes.BROWSER_OPEN_CHROME_TAB})
public class SingleSignOn extends Plugin {
    public static final String CUSTOM_TAB_PACKAGE_NAME = "com.android.chrome";

    private CustomTabsClient customTabsClient;
    private CustomTabsSession currentSession;

    @PluginMethod()
    public void authenticate(PluginCall call) {
        String url = call.getString("url");

        if (url == null) {
            call.error("Must provide a URL to open");
            return;
        }
        if (url.isEmpty()) {
            call.error("URL must not be empty");
            return;
        }

        saveCall(call);
        open(url);
    }


    protected void open(String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder(getCustomTabsSession());

        CustomTabsIntent tabsIntent = builder.build();
        tabsIntent.intent.putExtra(Intent.EXTRA_REFERRER,
                Uri.parse(Intent.URI_ANDROID_APP_SCHEME + "//" + getContext().getPackageName()));
        tabsIntent.launchUrl(getContext(), Uri.parse(url));
    }


    CustomTabsServiceConnection connection = new CustomTabsServiceConnection() {
        @Override
        public void onCustomTabsServiceConnected(ComponentName name, CustomTabsClient client) {
            customTabsClient = client;
            client.warmup(0);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    public void load() {
    }

    protected void handleOnResume() {
        boolean ok = CustomTabsClient.bindCustomTabsService(getContext(), CUSTOM_TAB_PACKAGE_NAME, connection);
        if (!ok) {
            Log.e(getLogTag(), "Error binding to custom tabs service");
        }

        if (getSavedCall() != null) {
            getSavedCall().error("Login Unsuccessful");
            saveCall(null);
        }
    }

    protected void handleOnPause() {
        getContext().unbindService(connection);
    }

    public CustomTabsSession getCustomTabsSession() {
        if (customTabsClient == null) {
            return null;
        }

        if (currentSession == null) {
            currentSession = customTabsClient.newSession(null);
        }

        return currentSession;
    }

    @Override
    protected void handleOnNewIntent(Intent intent) {
        super.handleOnNewIntent(intent);
        Uri responseUri = intent.getData();
        if (responseUri != null) {
            JSObject d = new JSObject();
            d.put("url", responseUri.toString());
            if (getSavedCall() != null) {
                getSavedCall().success(d);
                saveCall(null);
            }
        }
    }

    @Override
    protected void handleOnActivityResult(int requestCode, int resultCode, Intent data) {
        super.handleOnActivityResult(requestCode, resultCode, data);
    }
}
