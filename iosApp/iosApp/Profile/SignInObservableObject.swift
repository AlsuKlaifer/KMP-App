//
//  SignInObservableObject.swift
//  iosApp
//
//  Created by Alsu Faizova on 30.05.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Combine
import shared

public class SignInObservableObject: ObservableObject {

    var viewModel: SignInViewModel

    @Published private(set) var state: SignInState

    init(wrapped: SignInViewModel) {
        viewModel = wrapped
        state = wrapped.viewStates.value
        (wrapped.viewStates.asPublisher() as AnyPublisher<SignInState, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$state)
    }
}

public extension SignInViewModel {

    func asObserveableObject() -> SignInObservableObject {
        return SignInObservableObject(wrapped: self)
    }
}
