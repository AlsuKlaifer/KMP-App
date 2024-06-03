//
//  DetailView.swift
//  iosApp
//
//  Created by Alsu Faizova on 11.05.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DetailView: View {
    let state: DetailState
    let eventConsumer: (DetailEvent) -> Void
    
    var body: some View {
        HStack {
            Button(action: {
                eventConsumer(DetailEventOnBackClicked())
            }) {
                Image(systemName: "arrow.left")
                    .foregroundColor(.primary)
                    .padding()
            }
            Spacer()
        }
        if let article = state.article {
            ScrollView {
                VStack(alignment: .leading, spacing: 16) {
                    if let imageString = article.urlToImage,
                       let urlToImage = URL(string: imageString) {
                        AsyncImageView(url: urlToImage, placeholder: Image(imageString))
                            .aspectRatio(contentMode: .fill)
                            .frame(height: UIScreen.main.bounds.height / 5)
                            .clipped()
                    }
                    Text(article.title ?? "")
                        .font(.title)
                    
                    Text(article.author ?? "")
                        .font(.subheadline)
                        .foregroundColor(.secondary)
                    
                    Text(article.content ?? "")
                        .padding(.top, 8)
                        .fixedSize(horizontal: false, vertical: true)
                }
                .padding()
                .padding(.horizontal)
            }
        }
    }
}
