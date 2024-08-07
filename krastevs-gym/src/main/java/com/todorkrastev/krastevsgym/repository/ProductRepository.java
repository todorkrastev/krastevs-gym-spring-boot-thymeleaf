package com.todorkrastev.krastevsgym.repository;

import com.todorkrastev.krastevsgym.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findAllByDepartmentCategory_IdOrderByCreatedAtDesc(Long departmentCategoryId);

    List<ProductEntity> findAllByPriceBetweenAndDepartmentCategory_IdOrderByCreatedAtDesc(BigDecimal from, BigDecimal to, Long departmentId);

    List<ProductEntity> findAllByDepartmentCategory_IdAndCategory_IdOrderByCreatedAtDesc(Long departmentId, Long categoryId);
}
