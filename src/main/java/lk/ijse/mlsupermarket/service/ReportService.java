package lk.ijse.mlsupermarket.service;

import lk.ijse.mlsupermarket.dto.response.DashboardDTO;
import lk.ijse.mlsupermarket.dto.response.SalesReportDTO;
import lk.ijse.mlsupermarket.dto.response.StockReportDTO;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {

    List<SalesReportDTO> getSalesReport(LocalDate startDate, LocalDate endDate);
    List<StockReportDTO> getStockReport();
    DashboardDTO getDashboardSummary();
}