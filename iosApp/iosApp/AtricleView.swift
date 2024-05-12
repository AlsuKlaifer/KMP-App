//
//  AtricleView.swift
//  iosApp
//
//  Created by Alsu Faizova on 11.05.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct ArticleView: View {
    let item: ListItem
    
    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 16) {
                Image(systemName: item.imageName)
                    .resizable()
                    .aspectRatio(contentMode: .fill)
                    .frame(height: 300)
                    .clipped()
                
                Text(item.title)
                    .font(.title)
                
                Text(item.author)
                    .font(.subheadline)
                    .foregroundColor(.secondary)
                
                Text(item.text ?? loremIpsum)
                    .padding(.top, 8)
                    .fixedSize(horizontal: false, vertical: true)
            }
            .padding()
            .padding(.horizontal)
        }
        .navigationBarTitle("", displayMode: .inline)
    }
    
    let loremIpsum = """
    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer eget erat nec ipsum posuere blandit. In efficitur magna at mi faucibus, quis dignissim quam lacinia. Integer consequat, sapien non tempor cursus, sem purus dictum dolor, nec ullamcorper dui libero et leo. Duis tristique erat sit amet aliquet dictum. Nulla facilisi. Integer nec tempus nisi, et eleifend nisl. Nam volutpat fringilla felis in eleifend. Nunc id mi non elit bibendum consectetur a eget odio. Nullam vel felis at libero tincidunt accumsan.
    
    Sed non diam et lorem auctor scelerisque. Fusce id enim mi. Nulla viverra felis sed erat venenatis, id mattis purus luctus. Phasellus lobortis venenatis justo, non feugiat nisi. Fusce quis convallis felis. In a risus quis ligula vehicula ultricies. Donec convallis posuere augue. Vivamus finibus est quis lorem scelerisque, id luctus ipsum rutrum. Morbi non diam nunc. Sed lobortis pretium felis, nec malesuada nulla rhoncus in. Ut at elit sed sapien commodo condimentum.
    
    Maecenas nec mauris non dolor rutrum facilisis. Cras id lorem nec sem finibus posuere. Proin suscipit nisi et nulla viverra iaculis. Aliquam erat volutpat. Sed eleifend, odio eu lacinia aliquet, velit urna commodo felis, nec fermentum augue risus sed libero. Cras aliquam velit at arcu eleifend, nec sodales enim luctus. Sed vehicula, justo ac luctus rutrum, odio tortor faucibus nulla, eget sodales sapien magna ut nisl. Suspendisse nec sem nec magna egestas lacinia vitae at felis. Nulla et posuere nulla. Integer lobortis est a est aliquam, eget cursus urna laoreet. Integer aliquam libero in commodo viverra.
    
    """
}

struct ArticleView_Previews: PreviewProvider {
    static var previews: some View {
        ArticleView(item: ListItem(imageName: "airplane", title: "Title", author: "Author"))
    }
}
