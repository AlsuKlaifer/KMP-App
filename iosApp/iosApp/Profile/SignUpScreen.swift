//
//  SignUpScreen.swift
//  iosApp
//
//  Created by Alsu Faizova on 04.06.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SignUpView: View {
    let state: SignUpState
    let eventConsumer: (SignUpEvent) -> Void

    @State var username: String = ""
    @State var email: String = ""
    @State var password: String = ""
        
    var body: some View {
        VStack {
            HStack {
                Button(action: {
                    eventConsumer(SignUpEventOnBackClicked())
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
                    Text("Sign Up")
                        .font(.title)
                        .padding(.leading)
                    Spacer()
                }
                TextField("Username", text: $username)
                    .padding()
                    .background(Color(.systemGray6))
                    .cornerRadius(8)
                    .padding(.horizontal, 20)
                    .onChange(of: email) { newUsername in
                        eventConsumer(SignUpEventOnUsernameQueryChanged(newUsername: newUsername))
                    }
                
                TextField("Email", text: $email)
                    .padding()
                    .background(Color(.systemGray6))
                    .cornerRadius(8)
                    .padding(.horizontal, 20)
                    .keyboardType(.emailAddress)
                    .autocapitalization(.none)
                    .onChange(of: email) { newEmail in
                        eventConsumer(SignUpEventOnEmailQueryChanged(newEmail: newEmail))
                    }
                
                SecureField("Password", text: $password)
                    .padding()
                    .background(Color(.systemGray6))
                    .cornerRadius(8)
                    .padding(.horizontal, 20)
                    .onChange(of: email) { newPassword in
                        eventConsumer(SignUpEventOnPasswordQueryChanged(newPassword: newPassword))
                    }
                
                Button(action: {
                    eventConsumer(SignUpEventOnSignUpClicked())
                }) {
                    Text("Sign Up")
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

struct SignUpScreen: View {
    @ObservedObject
    private var viewModel = ViewModels().getSignUpViewModel().asObserveableObject()

    @EnvironmentObject
    var navigator: ProfileNavigator

    var body: some View {
        SignUpView(
            state: viewModel.state,
            eventConsumer: { event in
                viewModel.viewModel.obtainEvent(event: event)
            })
        .onReceive(viewModel.viewModel.viewActions.asPublisher()) { action in
            handleAction(action: action)
        }
    }

    private func handleAction(action: SignUpAction?) {
        switch action {
        default:
            navigator.navigate(to: .profile)
        }
    }
}
