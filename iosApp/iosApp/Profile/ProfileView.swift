//
//  ProfileView.swift
//  iosApp
//
//  Created by Alsu Faizova on 12.05.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ProfileView: View {
    let state: ProfileState
    let eventConsumer: (ProfileEvent) -> Void

    var body: some View {
        if state.user == nil {
            VStack(spacing: 10) {
                Text("You are not authorized")
                    .font(.title)
                    .padding(.bottom)
                Button(action: {
                    eventConsumer(ProfileEventOnSignInClicked())
                }) {
                    Text("Sign in")
                        .padding()
                        .background(Color.blue)
                        .foregroundColor(.white)
                        .cornerRadius(30)
                }
                
                Button(action: {
                    eventConsumer(ProfileEventOnSignUpClicked())
                }) {
                    Text("Sign Up")
                        .padding()
                        .background(Color.blue)
                        .foregroundColor(.white)
                        .cornerRadius(30)
                }
            }
        } else {
            VStack {
                Image(systemName: "person.circle.fill")
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                    .frame(width: 120, height: 120)
                    .foregroundColor(.primary)
                    .padding(.top, 30)
                
                Text(state.user?.username ?? "")
                    .font(.title)
                    .foregroundStyle(.primary)
                    .fontWeight(.bold)
                    .padding(.top, 20)
                
                Text(state.user?.email ?? "")
                    .font(.headline)
                    .foregroundColor(.accentColor)
                
                Spacer()
            }
            .padding()
        }
    }
}
