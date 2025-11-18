package com.example.product_service.Repository;

import com.example.product_service.entity.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInterface extends JpaRepository<Product, Long> , JpaSpecificationExecutor<Product> {
    default Specification<Product> getSearchSpecification(String searchKeyword) {
        return ((root, query, criteriaBuilder) -> {
            if(searchKeyword == null || searchKeyword.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            String likePattern = "%" + searchKeyword.toLowerCase() + "%";
            return criteriaBuilder.or(
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), likePattern),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), likePattern),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("category")), likePattern)
                                     );
        });
    }
}
