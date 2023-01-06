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
            TextField("Name Of Borrower", text: $viewModel.noteTitle)
            //    .font(.title)
            TextField("Object Lent", text: $viewModel.noteContent)
                 TextField("Borrower Contact", text: $viewModel.noteContent)
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
        .background(Color(hex: viewModel.noteColor))
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
