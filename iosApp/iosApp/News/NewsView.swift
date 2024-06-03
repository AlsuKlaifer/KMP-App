//
//  NewsView.swift
//  iosApp
//
//  Created by Alsu Faizova on 11.05.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NewsView: View {
    let state: HomeState
    let eventConsumer: (HomeEvent) -> Void
    
    var body: some View {
        NavigationView {
            NewsListView(
                state: state,
                eventConsumer: eventConsumer
            ).navigationBarTitle("News")
        }
    }
}

struct NewsListView: View {
    let state: HomeState
    let eventConsumer: (HomeEvent) -> Void
    
    var body: some View {
        ScrollView {
            VStack(alignment: .center, spacing: 16) {
                ForEach(state.newsList, id: \.title) { item in
                    NewsItemView(item: item, eventConsumer: eventConsumer)
                }
            }
            .padding(.top)
        }
    }
}

struct NewsItemView: View {
    let item: shared.Article
    let eventConsumer: (HomeEvent) -> Void
    
    var body: some View {
        Button(action: {
            eventConsumer(HomeEventOnArticleClicked(article: item))
        }) {
            VStack(alignment: .center, spacing: 8) {
                if let imageString = item.urlToImage,
                   let urlToImage = URL(string: imageString) {
                    AsyncImageView(url: urlToImage, placeholder: Image(imageString))
                        .aspectRatio(contentMode: .fit)
                        .frame(height: UIScreen.main.bounds.height / 5)
                        .clipped()
                }
                
                Text(item.title ?? "")
                    .font(.headline)
                    .foregroundColor(Color(UIColor.label))
                
                Text(item.author ?? "Unknown")
                    .font(.subheadline)
                    .foregroundColor(.secondary)
            }
            .frame(maxWidth: .infinity)
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
}
