import SwiftUI
import shared

struct ContentView: View {

    let items: [ListItem] = [
        ListItem(imageName: "airplane", title: "Текст 1", author: "Автор"),
        ListItem(imageName: "airplane", title: "Текст 2", author: "Автор"),
        ListItem(imageName: "airplane", title: "Текст 3", author: "Автор"),
        ListItem(imageName: "airplane", title: "Текст 4", author: "Автор"),
        ListItem(imageName: "airplane", title: "Текст 5", author: "Автор"),
        ListItem(imageName: "airplane", title: "Текст 6", author: "Автор"),
    ]
    
    var body: some View {
        TabView {
            NewsView(items: items)
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