//
//  ProfileScreen.swift
//  iosApp
//
//  Created by Alsu Faizova on 04.06.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ProfileScreen: View {
    @ObservedObject
    private var viewModel = ViewModels().getProfileViewModel().asObserveableObject()

    @EnvironmentObject
    var profilenavigator: ProfileNavigator

    var body: some View {
        ProfileView(
            state: viewModel.state,
            eventConsumer: { event in
                viewModel.viewModel.obtainEvent(event: event)
            })
        .onReceive(viewModel.viewModel.viewActions.asPublisher()) { action in
            handleAction(action: action)
        }
    }

    private func handleAction(action: ProfileAction?) {
        switch action {
        case is ProfileActionNavigateToSignInScreen:
            profilenavigator.navigate(to: .signIn)
        case is ProfileActionNavigateToSignUpScreen:
            profilenavigator.navigate(to: .signUp)
        default:
            break
        }
    }
}
