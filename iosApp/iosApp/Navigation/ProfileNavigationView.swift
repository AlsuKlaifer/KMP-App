//
//  ProfileNavigationView.swift
//  iosApp
//
//  Created by Alsu Faizova on 03.06.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

class ProfileNavigator: ObservableObject {
    @Published var currentScreen: ProfileScreens = .profile

    func navigate(to screen: ProfileScreens) {
        currentScreen = screen
    }
}

struct ProfileNavigationView: View {
    @EnvironmentObject var navigator: ProfileNavigator

    var body: some View {
        switch navigator.currentScreen {
        case .profile:
            ProfileScreen()
        case .signIn:
            SignInScreen()
        case .signUp:
            SignUpScreen()
        }
   }
}

enum ProfileScreens: Hashable {
    case profile
    case signIn
    case signUp
}
