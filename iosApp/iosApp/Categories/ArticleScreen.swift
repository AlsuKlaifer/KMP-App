//
//  ArticleScreen.swift
//  iosApp
//
//  Created by Alsu Faizova on 03.06.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ArticleScreen: View {
    let title: String

    @EnvironmentObject
    var navigator: CategoryNavigator

    @ObservedObject
    private var viewModel: DetailObservableObject

    init(title: String) {
        self.title = title
        self.viewModel = ViewModels().getDetailsViewModel(title: title).asObserveableObject()
    }

    var body: some View {
        DetailView(
            state: viewModel.state,
            eventConsumer: { event in
                handleEvent(event)
            }
        )
        .navigationBarTitle("Article", displayMode: .inline)
        .navigationBarBackButtonHidden(true)
        .navigationBarItems(leading: Button(action: {
            navigator.navigate(to: .categories)
        }) {
            Image(systemName: "arrow.left")
                .foregroundColor(.white)
        })
    }

    private func handleEvent(_ event: DetailEvent) {
        switch event {
        case is DetailEventOnBackClicked:
            navigator.navigate(to: .categories)
        default:
            viewModel.viewModel.obtainEvent(event: event)
        }
    }
}
