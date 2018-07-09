import Foundation
import Capacitor
import SafariServices

typealias JSObject = [String:Any]
@objc(SingleSignOnPlugin)
public class SingleSignOnPlugin: CAPPlugin, SFSafariViewControllerDelegate {

    private var session: Any?

    @objc func authenticate(_ call: CAPPluginCall) {
        let url = call.getString("url") ?? ""
        let scheme = call.getString("customScheme") ?? ""
        if #available(iOS 11.0, *) {
            self.session = SFAuthenticationSession.init(url: URL(string: url)!, callbackURLScheme: scheme,completionHandler: {callback,error in
                if(error != nil){
                    call.reject("")
                }else{
                    var obj = JSObject()
                    obj["value"] = callback?.absoluteString
                   call.resolve(obj)
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
//                    call.resolve(obj)
//                }
//            })
//            (self.session as! ASWebAuthenticationSession ).start()
//        }
    }

}
