import SwiftUI
import shared

struct ContentView: View {
    var body: some View {
        TabView {
            NewsScreen()
                .tabItem {
                    Image(systemName: "newspaper")
                    Text("News")
                }
            CategoryView()
                .tabItem {
                    Image(systemName: "list.bullet")
                    Text("Categories")
                }
            ProfileView()
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
