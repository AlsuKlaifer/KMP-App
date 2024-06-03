//
//  CategoriesObservableObject.swift
//  iosApp
//
//  Created by Alsu Faizova on 04.06.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import shared

public class CategoriesObservableObject: ObservableObject {

    var viewModel: CategoriesViewModel

    @Published private(set) var state: CategoriesState

    init(wrapped: CategoriesViewModel) {
        viewModel = wrapped
        state = wrapped.viewStates.value
        (wrapped.viewStates.asPublisher() as AnyPublisher<CategoriesState, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$state)
    }
}

public extension CategoriesViewModel {

    func asObserveableObject() -> CategoriesObservableObject {
        return CategoriesObservableObject(wrapped: self)
    }
}
