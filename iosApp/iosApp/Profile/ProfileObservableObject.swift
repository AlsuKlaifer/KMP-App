//
//  ProfileObservableObject.swift
//  iosApp
//
//  Created by Alsu Faizova on 30.05.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Combine
import shared

public class ProfileObservableObject: ObservableObject {

    var viewModel: ProfileViewModel

    @Published private(set) var state: ProfileState

    init(wrapped: ProfileViewModel) {
        viewModel = wrapped
        state = wrapped.viewStates.value
        (wrapped.viewStates.asPublisher() as AnyPublisher<ProfileState, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$state)
    }
}

public extension ProfileViewModel {

    func asObserveableObject() -> ProfileObservableObject {
        return ProfileObservableObject(wrapped: self)
    }
}
