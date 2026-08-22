package lk.ijse.mlsupermarket.service.impl;

import lk.ijse.mlsupermarket.dto.response.DashboardDTO;
import lk.ijse.mlsupermarket.dto.response.SalesReportDTO;
import lk.ijse.mlsupermarket.dto.response.StockReportDTO;
import lk.ijse.mlsupermarket.repository.CustomerRepository;
import lk.ijse.mlsupermarket.repository.InventoryRepository;
import lk.ijse.mlsupermarket.repository.OrderRepository;
import lk.ijse.mlsupermarket.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {
    private final OrderRepository orderRepository;
    private final InventoryRepository inventoryRepository;
    private final CustomerRepository customerRepository;

    public ReportServiceImpl(OrderRepository orderRepository, InventoryRepository inventoryRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.inventoryRepository = inventoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<SalesReportDTO> getSalesReport(LocalDate startDate, LocalDate endDate) {
        log.info("Execute getSalesReport()", startDate, endDate);
        try {
            return orderRepository.getSalesReport(startDate, endDate);
        } catch (Exception e) {
            log.error("Error in getSalesReport() " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<StockReportDTO> getStockReport() {
        log.info("Execute getStockReport()");
        try {
            return inventoryRepository.getStockReport();
        } catch (Exception e) {
            log.error("Error in getStockReport()" + e.getMessage());
            throw e;
        }
    }

    @Override
    public DashboardDTO getDashboardSummary() {
        log.info("Execute getDashboardSummary()");
        try {
            long totalCustomers = customerRepository.count();
            long totalOrders = orderRepository.count();
            double totalRevenue = orderRepository.findAll().stream()
                    .mapToDouble(o -> o.getTotal() != null ? o.getTotal() : 0.0)
                    .sum();
            long lowStockCount = inventoryRepository.findAll().stream()
                    .filter(i -> i.getQuantity() <= i.getReorderLevel())
                    .count();

            return new DashboardDTO(totalCustomers, totalOrders, totalRevenue, lowStockCount);
        } catch (Exception e) {
            log.error("Error in getDashboardSummary()" + e.getMessage());
            throw e;
        }
    }
}