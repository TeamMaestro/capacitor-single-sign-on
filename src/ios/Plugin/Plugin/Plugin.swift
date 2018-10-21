import Foundation
import Capacitor
import SafariServices
import AuthenticationServices

typealias JSObject = [String:Any]
@objc(CAPSingleSignOnPlugin)
public class CAPSingleSignOnPlugin: CAPPlugin, SFSafariViewControllerDelegate {

    private var session: Any?

    @objc func authenticate(_ call: CAPPluginCall) {
        let url = call.getString("url") ?? ""
        let scheme = call.getString("customScheme") ?? ""
        if #available(iOS 12.0, *) {
            self.session = ASWebAuthenticationSession.init(url: URL(string: url)!, callbackURLScheme: scheme,completionHandler: {url,error in
                if(error != nil) {
                    call.reject("")
                }
                else {
                    var response = JSObject()
                    response["url"] = url?.absoluteString
                    call.resolve(response)
                }
            })
            (self.session as! ASWebAuthenticationSession).start()
        }
        else if #available(iOS 11.0, *) {
            self.session = SFAuthenticationSession.init(url: URL(string: url)!, callbackURLScheme: scheme,completionHandler: {url,error in
                if(error != nil) {
                    call.reject("")
                }
                else {
                    var response = JSObject()
                    response["url"] = url?.absoluteString
                    call.resolve(response)
                }
            })
            (self.session as! SFAuthenticationSession).start()
        }
        else {
            call.reject("Not supported")
        }
    }

}
