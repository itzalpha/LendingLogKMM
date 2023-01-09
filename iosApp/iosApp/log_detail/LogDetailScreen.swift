//
//  LogDetailScreen.swift
//  iosApp
//
//  Created by Abu Yazeed on 09/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct LogDetailScreen: View {
    private var logDataSource: logDataSource
    private var logId: Int64? = nil

    @StateObject var viewModel = LogDetailViewModel(logDataSource: nil)

    @Environment(\.presentationMode) var presentation

    init(logDataSource: logDataSource, logId: Int64? = nil) {
        self.logDataSource = logDataSource
        self.logId = logId
    }
    var body: some View {
        VStack(alignment: .leading) {
            TextField("Name Of Borrower", text: $viewModel.name)
            TextField("Object Lent", text: $viewModel.objectLent)
           TextField("Borrower Contact", text: $viewModel.time)
            Spacer()
        }.toolbar(content: {
            Button(action: {
                viewModel.saveNote {
                    self.presentation.wrappedValue.dismiss()
                }
            }) {
                Image(systemName: "checkmark")
            }
        })
        .padding()
        .onAppear {
            viewModel.setParamsAndLoadNote(logDataSource: logDataSource, logId: logId)
        }
    }
}

struct LogDetailScreen_Previews: PreviewProvider {
    static var previews: some View {
        EmptyView()
    }
}
