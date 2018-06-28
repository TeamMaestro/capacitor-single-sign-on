package co.fitcom.customtabs;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsCallback;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;

import net.openid.appauth.AuthorizationRequest;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.ResponseTypeValues;


@NativePlugin(
        requestCodes = {CustomTabsPlugin.SSO_REQUEST}
)
public class CustomTabsPlugin extends Plugin {
    public static final int SSO_REQUEST = 868;
    private CustomTabsClient mCustomTabsClient;
    private static final String CUSTOM_TAB_PACKAGE_NAME = "com.android.chrome";
    private CustomTabsCallback callback;
    private CustomTabsIntent intent;

    @Override
    public void load() {
        super.load();
        CustomTabsServiceConnection connection = new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(ComponentName name, CustomTabsClient client) {
                mCustomTabsClient = client;
                mCustomTabsClient.warmup(1);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mCustomTabsClient = null;
            }
        };

        CustomTabsClient.bindCustomTabsService(getContext(), CUSTOM_TAB_PACKAGE_NAME, connection);
    }

    public CustomTabsCallback getCallback() {
        return callback;
    }

    public CustomTabsIntent getIntent() {
        return intent;
    }

    @Override
    protected void handleOnActivityResult(int requestCode, int resultCode, Intent data) {
        super.handleOnActivityResult(requestCode, resultCode, data);

        PluginCall savedCall = getSavedCall();

        if (savedCall == null) {
            return;
        }

        if (requestCode == CustomTabsPlugin.SSO_REQUEST) {
            Uri d = data.getData();
            if (d != null) {
                JSObject obj = new JSObject();
                obj.put("value", d.toString());
                savedCall.resolve(obj);
            } else {
                savedCall.reject("");
            }

        }
    }


    @PluginMethod()
    public void show(final PluginCall call) {
        String url = call.getString("url");
        String scheme = call.getString("customScheme", "");
        AuthorizationServiceConfiguration configuration = new AuthorizationServiceConfiguration(Uri.parse(url), Uri.parse(url));
        AuthorizationRequest.Builder builder = new AuthorizationRequest.Builder(configuration, scheme.replace("://", ""), ResponseTypeValues.ID_TOKEN, Uri.parse(scheme));
        AuthorizationRequest request = builder.build();

        AuthorizationService authService = new AuthorizationService(this.getContext());
        Intent authIntent = authService.getAuthorizationRequestIntent(request);
        startActivityForResult(call, authIntent, CustomTabsPlugin.SSO_REQUEST);
    }

    @PluginMethod()
    public void view(final PluginCall call) {
        String url = call.getString("url");
        callback = new CustomTabsCallback() {
            @Override
            public void onNavigationEvent(int navigationEvent, Bundle e) {
                super.onNavigationEvent(navigationEvent, e);
                switch (navigationEvent) {
                    case NAVIGATION_STARTED:
                        break;
                    case NAVIGATION_FINISHED:
                        break;
                    case NAVIGATION_FAILED:
                        //call.reject("failed");
                        break;
                    case NAVIGATION_ABORTED:
                        call.error("cancelled");
                        break;
                    case TAB_SHOWN:
                        break;
                    case TAB_HIDDEN:
                        break;
                }
            }
        };

        final CustomTabsSession session = mCustomTabsClient.newSession(getCallback());
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder(session);
        intent = builder.build();
        intent.launchUrl(this.getContext(), Uri.parse(url));
    }


}
