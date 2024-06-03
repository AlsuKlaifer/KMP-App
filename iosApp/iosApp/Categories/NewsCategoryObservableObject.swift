//
//  NewsCategoryObservableObject.swift
//  iosApp
//
//  Created by Alsu Faizova on 04.06.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import shared

public class NewsCategoryObservableObject: ObservableObject {

    var viewModel: NewsCategoryViewModel

    @Published private(set) var state: NewsCategoryState

    init(wrapped: NewsCategoryViewModel) {
        viewModel = wrapped
        state = wrapped.viewStates.value
        (wrapped.viewStates.asPublisher() as AnyPublisher<NewsCategoryState, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$state)
    }
}

public extension NewsCategoryViewModel {

    func asObserveableObject() -> NewsCategoryObservableObject {
        return NewsCategoryObservableObject(wrapped: self)
    }
}
