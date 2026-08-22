package lk.ijse.mlsupermarket.controller;

import lk.ijse.mlsupermarket.constant.CommonResponse;
import lk.ijse.mlsupermarket.constant.ResponseCode;
import lk.ijse.mlsupermarket.constant.ResponseMessage;
import lk.ijse.mlsupermarket.dto.response.DashboardDTO;
import lk.ijse.mlsupermarket.dto.response.SalesReportDTO;
import lk.ijse.mlsupermarket.dto.response.StockReportDTO;
import lk.ijse.mlsupermarket.service.ReportService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping(value = "/sales", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getSalesReport(
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate
    ) {
        List<SalesReportDTO> report = reportService.getSalesReport(startDate, endDate);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, report, ResponseMessage.SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/stock", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getStockReport() {
        List<StockReportDTO> report = reportService.getStockReport();
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, report, ResponseMessage.SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/dashboard", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getDashboardSummary() {
        DashboardDTO dashboard = reportService.getDashboardSummary();
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, dashboard, ResponseMessage.SUCCESS_MESSAGE);
    }
}