//
//  SignInScreen.swift
//  iosApp
//
//  Created by Alsu Faizova on 04.06.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SignInView: View {
    let state: SignInState
    let eventConsumer: (SignInEvent) -> Void

    @State var email: String = ""
    @State var password: String = ""
        
    var body: some View {
        VStack {
            HStack {
                Button(action: {
                    eventConsumer(SignInEventOnBackClicked())
                }) {
                    Image(systemName: "arrow.left")
                        .foregroundColor(.primary)
                        .padding()
                }
                Spacer()
            }
            Spacer()
            VStack(spacing: 20) {
                HStack {
                    Text("Sign In")
                        .font(.title)
                        .padding(.leading)
                    Spacer()
                }
                TextField("Email", text: $email)
                    .padding()
                    .background(Color(.systemGray6))
                    .cornerRadius(8)
                    .padding(.horizontal, 20)
                    .keyboardType(.emailAddress)
                    .autocapitalization(.none)
                    .onChange(of: email) { newEmail in
                        eventConsumer(SignInEventOnEmailQueryChanged(newEmail: newEmail))
                    }
                
                SecureField("Password", text: $password)
                    .padding()
                    .background(Color(.systemGray6))
                    .cornerRadius(8)
                    .padding(.horizontal, 20)
                    .onChange(of: email) { newPassword in
                        eventConsumer(SignInEventOnPasswordQueryChanged(newPassword: newPassword))
                    }
                
                Button(action: {
                    eventConsumer(SignInEventOnSignInClicked())
                }) {
                    Text("Sign In")
                        .padding()
                        .background(Color.blue)
                        .foregroundColor(.white)
                        .cornerRadius(30)
                }
            }
            Spacer()
        }
    }
}

struct SignInScreen: View {
    @ObservedObject
    private var viewModel = ViewModels().getSignInViewModel().asObserveableObject()

    @EnvironmentObject
    var navigator: ProfileNavigator

    var body: some View {
        SignInView(
            state: viewModel.state,
            eventConsumer: { event in
                viewModel.viewModel.obtainEvent(event: event)
            })
        .onReceive(viewModel.viewModel.viewActions.asPublisher()) { action in
            handleAction(action: action)
        }
    }

    private func handleAction(action: SignInAction?) {
        switch action {
        default:
            navigator.navigate(to: .profile)
        }
    }
}
