//
//  NewsView.swift
//  iosApp
//
//  Created by Alsu Faizova on 11.05.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NewsScreen: View {
    @ObservedObject private var viewModel = ViewModels().getHomeViewModel().asObserveableObject()
//    @EnvironmentObject var navigator: Navigator

    var body: some View {
        NewsView(
            state: viewModel.state,
            eventConsumer: { event in
                viewModel.viewModel.obtainEvent(event: event)
            })
        .onReceive(viewModel.viewModel.action.asPublisher()) { action in
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

struct ListItem: Identifiable {
    var id = UUID()
    var imageName: String
    var title: String
    var author: String
    var text: String?
}

struct NewsView: View {
    let state: HomeState
    let eventConsumer: (HomeEvent) -> Void
    
    var body: some View {
        NavigationView {
            NewsListView(state: state)
                .navigationBarTitle("News")
        }
    }
}

struct NewsListView: View {
    let state: HomeState
    
    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 16) {
                ForEach(state.newsList, id: \.title) { item in
                    NavigationLink(destination: ArticleView(item: item)) {
                        NewsItemView(item: item)
                    }
                }
            }
            .padding(.top)
        }
    }
}

struct NewsItemView: View {
    let item: shared.Article
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            if let imageString = item.urlToImage,
                let urlToImage = URL(string: imageString) {
                AsyncImageView(url: urlToImage, placeholder: Image(imageString))
                    .aspectRatio(contentMode: .fill)
                    .frame(height: UIScreen.main.bounds.height / 5)
                    .clipped()
            }
            
            Text(item.title ?? "")
                .font(.headline)
                .foregroundColor(Color(UIColor.label))
            
            Text(item.author ?? "")
                .font(.subheadline)
                .foregroundColor(.secondary)
        }
        .padding()
        .background(Color.clear)
        .cornerRadius(10)
        .overlay(
            RoundedRectangle(cornerRadius: 10)
                .stroke(Color.secondary, lineWidth: 2)
        )
        .padding(.horizontal)
    }
}
