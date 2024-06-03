//
//  NewsScreen.swift
//  iosApp
//
//  Created by Alsu Faizova on 03.06.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NewsScreen: View {
    @ObservedObject 
    private var viewModel = ViewModels().getHomeViewModel().asObserveableObject()
    
    @EnvironmentObject
    var navigator: NewsNavigator

    var body: some View {
        NewsView(
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
            navigator.navigate(to: .details(article: event.title ?? ""))
        default:
            break
        }
    }
}
