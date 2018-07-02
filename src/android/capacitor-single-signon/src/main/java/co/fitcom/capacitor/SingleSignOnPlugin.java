package co.fitcom.capacitor;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;

import net.openid.appauth.AuthorizationRequest;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.ResponseTypeValues;

@NativePlugin(requestCodes = { SingleSignOnPlugin.SSO_REQUEST })
public class SingleSignOnPlugin extends Plugin {
    public static final int SSO_REQUEST = 868;

    @Override
    protected void handleOnActivityResult(int requestCode, int resultCode, Intent data) {
        super.handleOnActivityResult(requestCode, resultCode, data);

        PluginCall savedCall = getSavedCall();

        if (savedCall == null) {
            return;
        }

        if (requestCode == SingleSignOnPlugin.SSO_REQUEST) {
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
        AuthorizationServiceConfiguration configuration = new AuthorizationServiceConfiguration(Uri.parse(url),
                Uri.parse(url));
        AuthorizationRequest.Builder builder = new AuthorizationRequest.Builder(configuration,
                scheme.replace("://", ""), ResponseTypeValues.ID_TOKEN, Uri.parse(scheme));
        AuthorizationRequest request = builder.build();

        AuthorizationService authService = new AuthorizationService(this.getContext());
        Intent authIntent = authService.getAuthorizationRequestIntent(request);
        startActivityForResult(call, authIntent, CustomTabsPlugin.SSO_REQUEST);
    }

}
