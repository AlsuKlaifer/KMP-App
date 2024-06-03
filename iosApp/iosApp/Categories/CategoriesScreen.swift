//
//  CategoriesScreen.swift
//  iosApp
//
//  Created by Alsu Faizova on 04.06.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CategoriesView: View {
    let state: CategoriesState
    let eventConsumer: (CategoriesEvent) -> Void

//    let categories: [String] = [
//        "Business",
//        "Entertainment",
//        "Technology",
//        "Health",
//        "Sports",
//        "Science",
//        "Travel",
//        "Food",
//        "Fashion",
//        "Art"
//    ]
    
    var body: some View {
        NavigationView {
            ScrollView {
                VStack(spacing: 0) {
                    let categories = state.categories.map { model in
                        model.codeName
                    }
                    ForEach(categories, id: \.self) { category in
                        Button(action: {
                            eventConsumer(CategoriesEventOnCategoryClick(codeName: category))
                        }) {
                            CategoryCell(category: category, image: "folder")
                        }
                        .padding(EdgeInsets(top: 10, leading: 20, bottom: 10, trailing: 20))
                    }
                }
            }
            .navigationTitle("Categories")
        }
    }
}

struct CategoriesScreen: View {
    @ObservedObject
    private var viewModel = ViewModels().getCategoriesViewModel().asObserveableObject()
    
    @EnvironmentObject
    var navigator: CategoryNavigator

    var body: some View {
        CategoriesView(
            state: viewModel.state,
            eventConsumer: { event in
                viewModel.viewModel.obtainEvent(event: event)
            })
        .onReceive(viewModel.viewModel.viewActions.asPublisher()) { action in
            handleAction(action: action)
        }
    }

    private func handleAction(action: CategoriesAction?) {
        switch action {
        case let event as CategoriesActionNavigateToCategoryNews:
            navigator.navigate(to: .category(event.codeName))
        default:
            break
        }
    }
}

struct CategoryCell: View {
    let category: String
    let image: String
    
    var body: some View {
        HStack {
            Image(systemName: image)
                .foregroundColor(.blue)
                .padding(.trailing, 8)
            Text(category)
                .foregroundColor(.primary)
                .font(.title3)
            Spacer()
        }
        .padding()
        .background(Color(UIColor.systemBackground))
        .cornerRadius(10)
        .overlay(
            RoundedRectangle(cornerRadius: 10)
                .stroke(Color(UIColor.lightGray))
                .opacity(0.5)
        )
        .shadow(radius: 2)
    }
}
