import Foundation
import shared

extension LogDetailScreen {
    @MainActor class LogDetailViewModel: ObservableObject {
        private var logDataSource: logDataSource?
        
        private var : Int64? = nil
        @Published var logTitle = ""
        @Published var logContent = ""
        @Published private(set) var log Color = Log.Companion().generateRandomColor()
        
        init(logDataSource: LogDataSource? = nil) {
            self.logDataSource = logDataSource
        }
        
        func loadLogIfExists(id: Int64?) {
            if id != nil {
                self.logId = id
                logDataSource?.getLogById(id: id!, completionHandler: { log , error in
                    self.logTitle = log ?.title ?? ""
                    self.logContent = log ?.content ?? ""
                    self.log Color = log ?.colorHex ?? Log.Companion().generateRandomColor()
                })
            }
        }
        
        func saveLog(onSaved: @escaping () -> Void) {
            logDataSource?.insertlog (
                log : Log(id: logId == nil ? nil : KotlinLong(value: logId!), title: self.logTitle, content: self.logContent, colorHex: self.log Color, created: DateTimeUtil().now()), completionHandler: { error in
                    onSaved()
                })
        }
        
        func setParamsAndLoadLog(logDataSource: LogDataSource, logId: Int64?) {
            self.logDataSource = logDataSource
            loadLogIfExists(id: logId)
        }
    }
}
