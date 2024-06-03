//
//  DetailObservableObject.swift
//  iosApp
//
//  Created by Alsu Faizova on 03.06.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Combine
import shared

public class DetailObservableObject: ObservableObject {

    var viewModel: DetailViewModel

    @Published private(set) var state: DetailState

    init(wrapped: DetailViewModel) {
        viewModel = wrapped
        state = wrapped.viewStates.value
        (wrapped.viewStates.asPublisher() as AnyPublisher<DetailState, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$state)
    }
}

public extension DetailViewModel {

    func asObserveableObject() -> DetailObservableObject {
        return DetailObservableObject(wrapped: self)
    }
}
