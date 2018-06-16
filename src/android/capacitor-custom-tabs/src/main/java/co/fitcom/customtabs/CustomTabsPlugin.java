package co.fitcom.customtabs;

import android.content.ComponentName;
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


@NativePlugin()
public class CustomTabsPlugin extends Plugin {
    private CustomTabsClient mCustomTabsClient;
    private static final String CUSTOM_TAB_PACKAGE_NAME = "com.android.chrome";
    private CustomTabsCallback callback;
    private CustomTabsIntent intent;
    private static String mScheme;
    private static PluginCall mCall;
    private static Uri mData;
    @Override
    protected void handleOnResume() {
        super.handleOnResume();
        mData = getActivity().getIntent().getData();
        if(mCall != null){
           getBridge().saveCall(mCall);
            String scheme = "";
            if(mData != null){
                scheme = mData.getScheme();
            }
            if (mCall != null && scheme.equals(mScheme)) {
                if (mData != null) {
                    JSObject obj = new JSObject();
                    obj.put("value" ,mData.toString());
                    mCall.success(obj);
                }
            }
        }
    }


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


    @PluginMethod(
            returnType = PluginMethod.RETURN_CALLBACK
    )
    public void show(final PluginCall call) {
        String url = call.getString("url");
        mCall = call;
        mScheme = call.getString("customScheme", "").replace("://","");
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
