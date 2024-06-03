import SwiftUI
import UIKit
import shared

@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate

    @StateObject var navigator = NewsNavigator()

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

class AppDelegate: NSObject, UIApplicationDelegate {
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil) -> Bool {
        DiHelperKt.doInitKoin(conf: createConfiguration()) { _ in }
        return true
    }

    private func createConfiguration() -> Configuration {
        let platformConfiguration = PlatformConfiguration(
            appVersionName: Bundle.main.infoDictionary?["CFBundleShortVersionString"] as? String ?? "",
            appVersionNumber: Bundle.main.infoDictionary?["CFBundleVersion"] as? String ?? "",
            osVersion: UIDevice.current.systemVersion,
            deviceType: .phone
        )

        return Configuration(
            platformConfiguration: platformConfiguration,
            isDebug: false,
            firebaseCrashlyticsBindings: FirebaseCrashlytics()
        )
    }
}

class FirebaseCrashlytics: FirebaseCrashlyticsBindings {
    func logScreenEvent(event: ScreenEvent) {
        print(event)
    }
}
