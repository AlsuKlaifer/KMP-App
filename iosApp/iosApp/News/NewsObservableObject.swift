//
//  NewsObservableObject.swift
//  iosApp
//
//  Created by Alsu Faizova on 30.05.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Combine
import shared

public class HomeObservableObject: ObservableObject {

    var viewModel: HomeViewModel

    @Published private(set) var state: HomeState

    init(wrapped: HomeViewModel) {
        viewModel = wrapped
        state = wrapped.state.value as! HomeState
        (wrapped.state.asPublisher() as AnyPublisher<HomeState, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$state)
    }
}

public extension HomeViewModel {

    func asObserveableObject() -> HomeObservableObject {
        return HomeObservableObject(wrapped: self)
    }
}
