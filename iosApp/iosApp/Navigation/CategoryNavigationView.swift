//
//  CategoryNavigationView.swift
//  iosApp
//
//  Created by Alsu Faizova on 04.06.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

class CategoryNavigator: ObservableObject {
    @Published var currentScreen: CategoryScreen = .categories

    func navigate(to screen: CategoryScreen) {
        currentScreen = screen
    }
}

struct CategoryNavigationView: View {
    @EnvironmentObject var navigator: CategoryNavigator

    var body: some View {
        VStack {
            switch navigator.currentScreen {
            case .categories:
                CategoriesScreen()
            case .category(let category):
                CategoryNewsScreen(category: category)
            case .details(let title):
                ArticleScreen(title: title)
            }
        }
   }
}

enum CategoryScreen: Hashable {
    case categories
    case category(_ category: String)
    case details(article: String)
}
