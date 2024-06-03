//
//  CategoryView.swift
//  iosApp
//
//  Created by Alsu Faizova on 12.05.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CategoryView: View {

    let categories: [String] = [
        "Technology",
        "Health",
        "Sports",
        "Entertainment",
        "Science",
        "Business",
        "Travel",
        "Food",
        "Fashion",
        "Art"
    ]
    
    var body: some View {
        NavigationView {
            ScrollView {
                VStack(spacing: 0) {
                    ForEach(categories, id: \.self) { category in
                        NavigationLink(destination: NewsListViewSection()
                            .navigationBarTitleDisplayMode(.inline)
                        ) {
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

struct NewsListViewSection: View {
    @ObservedObject private var viewModel = ViewModels().getHomeViewModel().asObserveableObject()
        @EnvironmentObject var navigator: NewsNavigator

    var body: some View {
        NewsListView(
            state: viewModel.state,
            eventConsumer: { event in
                viewModel.viewModel.obtainEvent(event: event)
            })
        .onReceive(viewModel.viewModel.viewActions.asPublisher()) { action in
            handleAction(action: action)
        }
    }

    private func handleAction(action: HomeAction?) {
        switch action {
        case let event as HomeActionNavigateToDetails:
            break
        default:
            break
        }
    }
}

struct CategoryView_Previews: PreviewProvider {
    static var previews: some View {
        CategoryView()
    }
}
