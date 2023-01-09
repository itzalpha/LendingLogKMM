//
//  SearchBar.swift
//  iosApp
//
//  Created by Abu Yazeed on 09/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

struct SearchBar<Destination: View>: View {

    var onSearchToggled: () -> Void
    var destinationProvider: () -> Destination
    var isSearchActive: Bool
    @Binding var searchText: String

    var body: some View {
        HStack {
            TextField("search object lent & borrower", text: $searchText)
                .textFieldStyle(.roundedBorder)
                .opacity(isSearchActive ? 1 : 0)
            if !isSearchActive {
                Spacer()
            }
            Button(action: onSearchToggled) {
                Image(systemName: isSearchActive ? "xmark" : "magnifyingglass")
                    .foregroundColor(.black)
            }
            NavigationLink(destination: destinationProvider()) {
                Image(systemName: "plus")
                    .foregroundColor(.black)
            }
        }
    }
}

struct SearchBar_Previews: PreviewProvider {
    static var previews: some View {
        SearchBar(
            onSearchToggled: {},
            destinationProvider: { EmptyView() },
            isSearchActive: true,
            searchText: .constant("....")
        )
    }
}
