import Foundation
import Capacitor
import SafariServices
import AuthenticationServices

typealias JSObject = [String:Any]
@objc(SingleSignOnPlugin)
public class SingleSignOnPlugin: CAPPlugin, ASWebAuthenticationPresentationContextProviding {

    @available(iOS 12.0, *)
    public func presentationAnchor(for session: ASWebAuthenticationSession) -> ASPresentationAnchor {
        return DispatchQueue.main.sync {
           return UIApplication.shared.keyWindow!
        }
    }

    private var session: Any?

    @objc func authenticate(_ call: CAPPluginCall) {
        let url = call.getString("url") ?? ""
        let scheme = call.getString("customScheme") ?? ""
        let prefersEphemeralWebBrowserSession = call.getBool("prefersEphemeralWebBrowserSession", false)

        if #available(iOS 12.0, *) {
            self.session = ASWebAuthenticationSession.init(url: URL(string: url)!, callbackURLScheme: scheme, completionHandler: { url, error in
                if (error != nil) {
                    call.reject("Error", error?.localizedDescription)
                }
                else {
                    var response = JSObject()
                    response["url"] = url?.absoluteString
                    call.resolve(response)
                }
            })
            if #available(iOS 13.0, *) {
                (self.session as! ASWebAuthenticationSession).prefersEphemeralWebBrowserSession = prefersEphemeralWebBrowserSession
                (self.session as! ASWebAuthenticationSession).presentationContextProvider = self
            }
            (self.session as! ASWebAuthenticationSession).start()
        }
        else {
            call.reject("Not supported")
        }
    }

}
