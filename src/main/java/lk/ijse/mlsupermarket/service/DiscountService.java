package lk.ijse.mlsupermarket.service;

import lk.ijse.mlsupermarket.dto.DiscountDTO;

import java.util.List;

public interface DiscountService {
    public void saveDiscount(DiscountDTO discountDTO);
    public void updateDiscount(DiscountDTO discountDTO);
    public List<DiscountDTO> getAllDiscounts();
    public List<DiscountDTO> getDiscountsByProduct(long productId);
    public void deleteDiscount(long discountId);
}