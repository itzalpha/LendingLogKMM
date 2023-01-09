//
//  LogListViewModel.swift
//  iosApp
//
//  Created by Abu Yazeed on 09/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension LogListScreen {
    @MainActor class LogListViewModel: ObservableObject {
        private var logDataSource: LogDataSource? = nil

        private let searchLogs = SearchLogs()

        private var logs = [Log]()
        @Published private(set) var filteredLogs = [Log]()
        @Published var searchText = "" {
            didSet {
                self.filteredLogs = searchLogs.execute(logs: self.logs, query: searchText)
            }
        }
        @Published private(set) var isSearchActive = false

        init(logDataSource: LogDataSource? = nil) {
            self.logDataSource = logDataSource
        }

        func loadLogs() {
            logDataSource?.getAllLogs(completionHandler: { logs, error in
                self.logs = logs ?? []
                self.filteredLogs = self.logs
            })
        }

        func deleteLogById(id: Int64?) {
            if id != nil {
                logDataSource?.deleteLogById(id: id!, completionHandler: { error in
                    self.loadLogs()
                })
            }
        }

        func toggleIsSearchActive() {
            isSearchActive = !isSearchActive
            if !isSearchActive {
                searchText = ""
            }
        }

        func setLogDataSource(logDataSource: LogDataSource) {
            self.logDataSource = logDataSource
        }
    }
}
