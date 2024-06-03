//
//  NewsNavigationView.swift
//  iosApp
//
//  Created by Alsu Faizova on 03.06.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

class NewsNavigator: ObservableObject {
    @Published var currentScreen: MainScreen = .home

    func navigate(to screen: MainScreen) {
        currentScreen = screen
    }
}

struct NewsNavigationView: View {
    @EnvironmentObject var navigator: NewsNavigator

    var body: some View {
        switch navigator.currentScreen {
        case .home:
            NewsScreen()
        case .details(let title):
            DetailScreen(title: title)
        }
   }
}

enum MainScreen: Hashable {
    case home
    case details(article: String)
}
