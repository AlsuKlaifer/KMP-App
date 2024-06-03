//
//  SignUpObservableObject.swift
//  iosApp
//
//  Created by Alsu Faizova on 30.05.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Combine
import shared

public class SignUpObservableObject: ObservableObject {

    var viewModel: SignUpViewModel

    @Published private(set) var state: SignUpState

    init(wrapped: SignUpViewModel) {
        viewModel = wrapped
        state = wrapped.viewStates.value
        (wrapped.viewStates.asPublisher() as AnyPublisher<SignUpState, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$state)
    }
}

public extension SignUpViewModel {

    func asObserveableObject() -> SignUpObservableObject {
        return SignUpObservableObject(wrapped: self)
    }
}
