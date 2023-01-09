//
//  LogDetailViewModel.swift
//  iosApp
//
//  Created by Abu Yazeed on 09/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension LogDetailScreen {
    @MainActor class LogDetailViewModel: ObservableObject {
        private var logDataSource: logDataSource?

        private var : Int64? = nil
        @Published var logName = ""
        @Published var logObjectLent = ""
       @Published var logTime = ""

        init(logDataSource: LogDataSource? = nil) {
            self.logDataSource = logDataSource
        }

        func loadLogIfExists(id: Int64?) {
            if id != nil {
                self.logId = id
                logDataSource?.getLogById(id: id!, completionHandler: { log , error in
                    self.logName = log ?.name ?? ""
                    self.logObjectLent = log ?.objectLent ?? ""
                     self.logTime = log ?.time ?? ""
                })
            }
        }

        func saveLog(onSaved: @escaping () -> Void) {
            logDataSource?.insertLog (
                log : Log(id: logId == nil ? nil : KotlinLong(value: logId!), name: self.logName, objectLent: self.logObjectLent, time:self.logTime, created: DateTimeUtil().now()), completionHandler: { error in
                    onSaved()
                })
        }

        func setParamsAndLoadLog(logDataSource: LogDataSource, logId: Int64?) {
            self.logDataSource = logDataSource
            loadLogIfExists(id: logId)
        }
    }
}