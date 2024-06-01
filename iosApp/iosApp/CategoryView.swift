//
//  CategoryView.swift
//  iosApp
//
//  Created by Alsu Faizova on 12.05.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct CategoryView: View {

    let categories: [String] = [
        "Technology",
        "Health",
        "Sports",
        "Entertainment",
        "Science",
        "Business",
        "Travel",
        "Food",
        "Fashion",
        "Art"
    ]
    
    var body: some View {
        NavigationView {
            ScrollView {
                VStack(spacing: 0) {
                    ForEach(categories, id: \.self) { category in
                        NavigationLink(destination: NewsScreen()
                            .navigationBarTitleDisplayMode(.inline)
                        ) {
                            CategoryCell(category: category)
                        }
                        .padding(EdgeInsets(top: 10, leading: 20, bottom: 10, trailing: 20))
                    }
                }
            }
            .navigationTitle("Categories")
        }
    }
}

struct CategoryCell: View {
    let category: String
    
    var body: some View {
        HStack {
            Image(systemName: "folder")
                .foregroundColor(.blue)
                .padding(.trailing, 8)
            Text(category)
                .foregroundColor(.primary)
                .font(.title3)
            Spacer()
        }
        .padding()
        .background(Color(UIColor.systemBackground))
        .cornerRadius(10)
        .overlay(
            RoundedRectangle(cornerRadius: 10)
                .stroke(Color(UIColor.lightGray))
                .opacity(0.5)
        )
        .shadow(radius: 2)
    }
}

struct CategoryView_Previews: PreviewProvider {
    static var previews: some View {
        CategoryView()
    }
}
