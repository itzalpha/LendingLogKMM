import SwiftUI
import shared

struct LogItem: View {
    var log: Log
    var onDeleteClick: () -> Void
    
    var body: some View {
        VStack(alignment: .leading) {
            HStack {
                Text(log.title)
                    .font(.title3)
                    .fontWeight(.semibold)
                Spacer()
                Button(action: onDeleteClick) {
                    Image(systemName: "xmark").foregroundColor(.black)
                }
            }.padding(.bottom, 3)
            
            Text(log.content)
                .fontWeight(.light)
                .padding(.bottom, 3)
            
            HStack {
                Spacer()
                Text(DateTimeUtil().formatLogDate(dateTime: log.created))
                    .font(.footlog)
                    .fontWeight(.light)
            }
        }
        .padding()
        .background(Color(hex: log.colorHex))
        .clipShape(RoundedRectangle(cornerRadius: 5.0))
    }
}

struct LogItem_Previews: PreviewProvider {
    static var previews: some View {
        LogItem(
            log: Log(id: nil, title: "My log", content: "Log content", colorHex: 0xFF2341, created: DateTimeUtil().now()),
            onDeleteClick: {}
        )
    }
}
