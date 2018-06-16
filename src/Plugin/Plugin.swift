import Foundation
import Capacitor
import SafariServices


let kSafariViewControllerCloseNotification = "kSafariViewControllerCloseNotification"
typealias JSObject = [String:Any]
@objc(CustomTabsPlugin)
public class CustomTabsPlugin: CAPPlugin, SFSafariViewControllerDelegate {
    private var controller : SFSafariViewController?
    private var _call: CAPPluginCall?
    public override func load() {
        NotificationCenter.default.addObserver(self, selector: #selector(self.login(notification:)), name: NSNotification.Name(rawValue: kSafariViewControllerCloseNotification), object: nil)
    }
    
    @objc func show(_ call: CAPPluginCall) {
        let url = call.getString("url") ?? ""
        _ = call.getString("customeScheme") ?? ""
        _call = call;
        self.controller = SFSafariViewController(url: URL.init(string: url)!)
        controller?.delegate = self
        self.bridge.viewController.present(self.controller!, animated: true) {
            
        }
    }
    
    
    
    public func safariViewControllerDidFinish(_ controller: SFSafariViewController) {
        
    }
    
    @objc func login(notification: NSNotification) {
        // get the url from the auth callback
        let url = notification.object as! NSURL
        var obj = JSObject()
        obj["value"] = url
        self._call?.success(obj)
        self.controller!.dismiss(animated: true, completion: nil)
    }
    
    
    func safariViewControllerDidFinish(controller: SFSafariViewController) {
        controller.dismiss(animated: true) { () -> Void in
            self._call?.error("cancel")
        }
    }
    
}
