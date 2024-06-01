//
//  Image+Extensions.swift
//  iosApp
//
//  Created by Alsu Faizova on 01.06.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Combine

extension Image {
    static func asyncImage(url: URL, placeholder: Image = Image(systemName: "photo")) -> some View {
        AsyncImageView(url: url, placeholder: placeholder)
    }
}

struct AsyncImageView: View {
    @StateObject private var loader = ImageLoader()
    let url: URL
    let placeholder: Image

    var body: some View {
        image
            .onAppear {
            loader.load(from: url)
        }
            .onDisappear {
            loader.cancel()
        }
    }

    private var image: some View {
        Group {
            if let uiImage = loader.image {
                Image(uiImage: uiImage)
                    .resizable()
            } else {
                placeholder
                    .resizable()
            }
        }
    }
}

class ImageLoader: ObservableObject {
    @Published var image: UIImage? = nil
    private var cancellable: AnyCancellable?

    func load(from url: URL) {
        cancellable = URLSession.shared.dataTaskPublisher(for: url)
            .map { UIImage(data: $0.data) }
            .replaceError(with: nil)
            .receive(on: DispatchQueue.main)
            .assign(to: \.image, on: self)
    }

    func cancel() {
        cancellable?.cancel()
    }
}


extension Image {

    func imageModifier() -> some View {
        self.resizable().scaledToFit()
    }

    func iconModifier() -> some View {
        self.imageModifier().frame(maxWidth: 128)
            .foregroundColor(.green)
            .opacity(0.5)
    }
}
