//
//  AtricleView.swift
//  iosApp
//
//  Created by Alsu Faizova on 11.05.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ArticleView: View {
    let item: shared.Article
    
    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 16) {
                if let imageString = item.urlToImage,
                   let urlToImage = URL(string: imageString) {
                    AsyncImageView(url: urlToImage, placeholder: Image(imageString))
                        .aspectRatio(contentMode: .fill)
                        .frame(height: UIScreen.main.bounds.height / 5)
                        .clipped()
                }
                Text(item.title ?? "")
                    .font(.title)
                
                Text(item.author ?? "")
                    .font(.subheadline)
                    .foregroundColor(.secondary)
                
                Text(item.content ?? "")
                    .padding(.top, 8)
                    .fixedSize(horizontal: false, vertical: true)
            }
            .padding()
            .padding(.horizontal)
        }
        .navigationBarTitle("", displayMode: .inline)
    }
}
