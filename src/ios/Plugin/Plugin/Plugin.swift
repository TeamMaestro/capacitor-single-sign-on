import Foundation
import Capacitor
import SafariServices
//import AuthenticationServices


let kSafariViewControllerCloseNotification = "kSafariViewControllerCloseNotification"
typealias JSObject = [String:Any]
@objc(CustomTabsPlugin)
public class CustomTabsPlugin: CAPPlugin, SFSafariViewControllerDelegate {
    private var controller : SFSafariViewController?
    private var _call: CAPPluginCall?
    private var session: Any?

    @objc func show(_ call: CAPPluginCall) {
        let url = call.getString("url") ?? ""
        let scheme = call.getString("customeScheme") ?? ""
        _call = call;
        if #available(iOS 11.0, *) {
            self.session = SFAuthenticationSession.init(url: URL(string: url)!, callbackURLScheme: scheme,completionHandler: {callback,error in
                if(error != nil){
                    call.reject("")
                }else{
                    var obj = JSObject()
                    obj["value"] = callback?.absoluteString
                   call.success(obj)
                }
            })
            (self.session as! SFAuthenticationSession ).start()
       }else{
           call.reject("Not supported")
       }
 //           else if #available(iOS 12.0, *) {
//            self.session = ASWebAuthenticationSession.init(url: URL(string: url)!, callbackURLScheme: scheme,completionHandler: {callback,error in
//                if(error != nil){
//                    call.reject("")
//                }else{
//                    var obj = JSObject()
//                    obj["value"] = callback?.absoluteString
//                    call.success(obj)
//                }
//            })
//            (self.session as! ASWebAuthenticationSession ).start()
//        }
    }


    @objc func view(_ call: CAPPluginCall) {
        let url = call.getString("url") ?? ""
        _call = call;
        controller = SFSafariViewController(url:URL.init(string: url)!)
        controller?.delegate = self
        DispatchQueue.main.async {
            self.bridge.viewController.present(self.controller!, animated: true, completion: {

            })
        }
    }

    public func safariViewControllerDidFinish(_ controller: SFSafariViewController) {
        if(_call != nil){
            self._call?.resolve()
        }
    }


}
