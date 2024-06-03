//
//  CategoryNewsScreen.swift
//  iosApp
//
//  Created by Alsu Faizova on 12.05.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CategoryNewsScreen: View {
    let category: String

    @ObservedObject
    private var viewModel: NewsCategoryObservableObject

    @EnvironmentObject
    var navigator: CategoryNavigator

    init(category: String) {
        self.category = category
        self.viewModel = ViewModels().getNewsCategoryViewModel(categoryCodeName: category).asObserveableObject()
    }

    var body: some View {
        NewsCategoryListView(
            state: viewModel.state,
            eventConsumer: { event in
                viewModel.viewModel.obtainEvent(event: event)
            })
        .onReceive(viewModel.viewModel.viewActions.asPublisher()) { action in
            handleAction(action: action)
        }
    }

    private func handleAction(action: NewsCategoryAction?) {
        switch action {
        case let event as NewsCategoryActionNavigateToDetails:
            navigator.navigate(to: .details(article: event.title ?? ""))
        case is NewsCategoryActionNavigateBack:
            navigator.navigate(to: .categories)
        default:
            break
        }
    }
}

struct NewsCategoryListView: View {
    let state: NewsCategoryState
    let eventConsumer: (NewsCategoryEvent) -> Void
    
    var body: some View {
        HStack {
            Button(action: {
                eventConsumer(NewsCategoryEventOnBackClicked())
            }) {
                Image(systemName: "arrow.left")
                    .foregroundColor(.primary)
                    .padding()
            }
            Spacer()
        }
        Spacer()
        ScrollView {
            VStack(alignment: .center, spacing: 16) {
                ForEach(state.newsList, id: \.title) { item in
                    NewsCategoryItemView(item: item, eventConsumer: eventConsumer)
                }
            }
            .padding(.top)
        }
    }
}

struct NewsCategoryItemView: View {
    let item: shared.Article
    let eventConsumer: (NewsCategoryEvent) -> Void
    
    var body: some View {
        Button(action: {
            eventConsumer(NewsCategoryEventOnArticleClicked(article: item))
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
                
                Text(item.author ?? "Uknown")
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
