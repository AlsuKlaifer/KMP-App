//
//  NewsView.swift
//  iosApp
//
//  Created by Alsu Faizova on 11.05.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct ListItem: Identifiable {
    var id = UUID()
    var imageName: String
    var title: String
    var author: String
    var text: String?
}

struct NewsView: View {
    let items: [ListItem]
    
    var body: some View {
        NavigationView {
            NewsListView(items: items)
                .navigationBarTitle("News")
        }
    }
}

struct NewsListView: View {
    let items: [ListItem]
    
    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 16) {
                ForEach(items) { item in
                    NavigationLink(destination: ArticleView(item: item)) {
                        NewsItemView(item: item)
                    }
                }
            }
            .padding(.top)
        }
    }
}

struct NewsItemView: View {
    let item: ListItem
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Image(systemName: item.imageName)
                .resizable()
                .aspectRatio(contentMode: .fill)
                .frame(height: UIScreen.main.bounds.height / 5)
                .clipped()
            
            Text(item.title)
                .font(.headline)
                .foregroundColor(Color(UIColor.label))
            
            Text(item.author)
                .font(.subheadline)
                .foregroundColor(.secondary)
        }
        .padding()
        .background(Color.clear)
        .cornerRadius(10)
        .overlay(
            RoundedRectangle(cornerRadius: 10)
                .stroke(Color.secondary, lineWidth: 2)
        )
        .padding(.horizontal)
    }
}
