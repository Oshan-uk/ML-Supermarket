package lk.ijse.mlsupermarket.service.impl;

import lk.ijse.mlsupermarket.dto.DiscountDTO;
import lk.ijse.mlsupermarket.entity.Discount;
import lk.ijse.mlsupermarket.entity.Product;
import lk.ijse.mlsupermarket.repository.DiscountRepository;
import lk.ijse.mlsupermarket.repository.ProductRepository;
import lk.ijse.mlsupermarket.service.DiscountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository discountRepository;
    private final ProductRepository productRepository;

    public DiscountServiceImpl(DiscountRepository discountRepository, ProductRepository productRepository) {
        this.discountRepository = discountRepository;
        this.productRepository = productRepository;
    }

    List<DiscountDTO> discountDTOList = new ArrayList<>();

    @Override
    public void saveDiscount(DiscountDTO discountDTO) {
        log.info("Execute saveDiscount()");

        try {
            Optional<Product> optionalProduct = productRepository.findById(discountDTO.getProductId());
            if (optionalProduct.isEmpty()) throw new RuntimeException("Sorry, related product is not found");

            Discount discount = new Discount();
            discount.setDiscountName(discountDTO.getDiscountName());
            discount.setDiscountPercentage(discountDTO.getDiscountPercentage());
            discount.setStartDate(discountDTO.getStartDate());
            discount.setEndDate(discountDTO.getEndDate());
            discount.setProduct(optionalProduct.get());

            discountRepository.save(discount);

        } catch (Exception e) {
            log.error("Error in saveDiscount() ");
            throw e;
        }
    }

    @Override
    public void updateDiscount(DiscountDTO discountDTO) {
        log.info("Execute updateDiscount()");

        try {
            Optional<Discount> optionalDiscount = discountRepository.findById(discountDTO.getDiscountId());
            if (optionalDiscount.isEmpty()) throw new RuntimeException("Sorry, related discount is not found");

            Discount discount = optionalDiscount.get();
            discount.setDiscountName(discountDTO.getDiscountName());
            discount.setDiscountPercentage(discountDTO.getDiscountPercentage());
            discount.setStartDate(discountDTO.getStartDate());
            discount.setEndDate(discountDTO.getEndDate());

            discountRepository.save(discount);

        } catch (Exception e) {
            log.error("Error in updateDiscount()");
            throw e;
        }
    }


    @Override
    public List<DiscountDTO> getAllDiscounts() {
        log.info("Execute getAllDiscounts()");

        try {
            List<Discount> discounts = discountRepository.findAll();


            for (Discount discount : discounts) {
                DiscountDTO discountDTO = new DiscountDTO(
                        discount.getDiscountId(),
                        discount.getDiscountName(),
                        discount.getDiscountPercentage(),
                        discount.getStartDate(),
                        discount.getEndDate(),
                        discount.getProduct().getProductId()
                );

                discountDTOList.add(discountDTO);
            }
            return discountDTOList;

        } catch (Exception e) {
            log.error("Error in getAllDiscounts()", e);
            throw e;
        }
    }

    @Override
    public List<DiscountDTO> getDiscountsByProduct(long productId) {
        log.info("Execute getDiscountsByProduct()");

        try {
            List<Discount> discounts = discountRepository.findByProduct_ProductId(productId);

            for (Discount discount : discounts) {
                DiscountDTO discountDTO = new DiscountDTO(
                        discount.getDiscountId(),
                        discount.getDiscountName(),
                        discount.getDiscountPercentage(),
                        discount.getStartDate(),
                        discount.getEndDate(),
                        discount.getProduct().getProductId()
                );

                discountDTOList.add(discountDTO);
            }

            return discountDTOList;
        } catch (Exception e) {
            log.error("Error in getDiscountsByProduct()", e);
            throw e;
        }
    }

    @Override
    public void deleteDiscount(long discountId) {
        log.info("Execute deleteDiscount()");
        try {
            Optional<Discount> optionalDiscount = discountRepository.findById(discountId);
            if (optionalDiscount.isEmpty()) throw new RuntimeException("Sorry, related discount is not found");

            discountRepository.deleteById(discountId);

        } catch (Exception e) {
            log.error("Error in deleteDiscount()");
            throw e;
        }
    }
}