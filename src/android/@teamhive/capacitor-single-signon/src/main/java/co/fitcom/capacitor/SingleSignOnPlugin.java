package co.fitcom.capacitor;

import android.content.Intent;
import android.net.Uri;

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
            Uri url = data.getData();
            if (url != null) {
                JSObject response = new JSObject();
                response.put("url", url.toString());

                savedCall.resolve(response);
            } else {
                savedCall.reject("");
            }

        }
    }

    @PluginMethod()
    public void authenticate(final PluginCall call) {
        String url = call.getString("url");
        String scheme = call.getString("customScheme", "");
        AuthorizationServiceConfiguration configuration = new AuthorizationServiceConfiguration(Uri.parse(url),
                Uri.parse(url));
        AuthorizationRequest.Builder builder = new AuthorizationRequest.Builder(configuration,
                scheme.replace("://", ""), ResponseTypeValues.ID_TOKEN, Uri.parse(scheme));
        AuthorizationRequest request = builder.build();

        AuthorizationService authService = new AuthorizationService(this.getContext());
        Intent authIntent = authService.getAuthorizationRequestIntent(request);
        startActivityForResult(call, authIntent, SingleSignOnPlugin.SSO_REQUEST);
    }

}
