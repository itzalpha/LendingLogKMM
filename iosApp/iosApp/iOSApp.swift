import SwiftUI
import shared

@main
struct iOSApp: App {
   
    private let databaseModule = databaseModule()
    var body: some Scene{

        WindowGroup {
            NavigationView {
                LogListScreen(logDataSource: databaseModule.logDataSource)
            }.accentColor(.black)
        }
	}
}
