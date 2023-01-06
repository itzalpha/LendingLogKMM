import SwiftUI
import shared

struct LogListScreen: View {
    private var logDataSource: LogDataSource
    @StateObject var viewModel = LogListViewModel(logDataSource: nil)
    
    @State private var isLogSelected = false
    @State private var selectedLogId: Int64? = nil
    
    init(logDataSource: LogDataSource) {
        self.logDataSource = logDataSource
    }
    
    var body: some View {
        VStack {
            ZStack {
                NavigationLink(destination: LogDetailScreen(logDataSource: self.logDataSource, logId: selectedLogId), isActive: $isLogSelected) {
                    EmptyView()
                }.hidden()
                HideableSearchTextField<LogDetailScreen>(onSearchToggled: {
                    viewModel.toggleIsSearchActive()
                }, destinationProvider: {
                    LogDetailScreen(
                        logDataSource: logDataSource,
                        logId: selectedLogId
                    )
                }, isSearchActive: viewModel.isSearchActive, searchText: $viewModel.searchText)
                .frame(maxWidth: .infinity, minHeight: 40)
                .padding()
                
                if !viewModel.isSearchActive {
                    Text("All logs")
                        .font(.title2)
                }
            }
            
            List {
                ForEach(viewModel.filteredLogs, id: \.self.id) { log in
                    Button(action: {
                        isLogSelected = true
                        selectedLogId = log.id?.int64Value
                    }) {
                        LogItem(log: log, onDeleteClick: {
                            viewModel.deleteLogById(id: log.id?.int64Value)
                        })
                    }
                }
            }
            .onAppear {
                viewModel.loadLogs()
            }
            .listStyle(.plain)
            .listRowSeparator(.hidden)
        }
        .onAppear {
            viewModel.setLogDataSource(logDataSource: logDataSource)
        }
    }
}

struct LogListScreen_Previews: PreviewProvider {
    static var previews: some View {
        EmptyView()
    }
}
