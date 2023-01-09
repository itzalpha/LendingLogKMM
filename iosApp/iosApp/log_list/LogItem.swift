//
//  LogItem.swift
//  iosApp
//
//  Created by Abu Yazeed on 09/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct LogItem: View {
    var log: Log
    var onDeleteClick: () -> Void

    var body: some View {
        VStack(alignment: .leading) {
            HStack {
              Text(DateTimeUtil().formatLogDate(dateTime: log.created))
                    .font()
                    .fontWeight(.semibold)
                Spacer()
                Button(action: onDeleteClick) {
                    Image(systemName: "xmark").foregroundColor(.black)
                }
            }.padding(.bottom, 3)

            Text("Object Lent" + log.objectLent)
                .fontWeight(.light)
                .padding(.bottom, 3)

        }
        .padding()
        .clipShape(RoundedRectangle(cornerRadius: 5.0))
    }
}

struct LogItem_Previews: PreviewProvider {
    static var previews: some View {
        LogItem(
            log: Log(id: nil, name: "Borrower Name", objectLent: "Object Lent", time: "Borrower Contact", created: DateTimeUtil().now()),
            onDeleteClick: {}
        )
    }
}
