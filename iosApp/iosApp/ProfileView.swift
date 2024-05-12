//
//  ProfileView.swift
//  iosApp
//
//  Created by Alsu Faizova on 12.05.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct ProfileView: View {
    var body: some View {
        NavigationView {
            VStack {
                Image(systemName: "person.circle.fill")
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                    .frame(width: 120, height: 120)
                    .foregroundColor(.primary)
                    .padding(.top, 30)
                
                Text("John Doe")
                    .font(.title)
                    .foregroundStyle(.primary)
                    .fontWeight(.bold)
                    .padding(.top, 20)
                
                Text("john.doe@example.com")
                    .font(.headline)
                    .foregroundColor(.accentColor)
                
                Spacer()
            }
            .padding()
        }
    }
}

struct ProfileView_Previews: PreviewProvider {
    static var previews: some View {
        ProfileView()
    }
}
