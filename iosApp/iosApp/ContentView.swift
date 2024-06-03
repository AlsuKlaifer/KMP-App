import SwiftUI
import shared

struct ContentView: View {
    @StateObject var navigator = NewsNavigator()
    @StateObject var profilenavigator = ProfileNavigator()
    @StateObject var categoryNavigator = CategoryNavigator()

    var body: some View {
        TabView {
            NewsNavigationView().environmentObject(navigator)
                .tabItem {
                    Image(systemName: "newspaper")
                    Text("News")
                }
            CategoryNavigationView().environmentObject(categoryNavigator)
                .tabItem {
                    Image(systemName: "list.bullet")
                    Text("Categories")
                }
            ProfileNavigationView().environmentObject(profilenavigator)
                .tabItem {
                    Image(systemName: "person.fill")
                    Text("Profile")
                }
        }
        .onAppear {
            let appearance = UITabBarAppearance()
            appearance.backgroundImage = UIImage()
            appearance.backgroundColor = UIColor.systemBackground
            UITabBar.appearance().standardAppearance = appearance
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
