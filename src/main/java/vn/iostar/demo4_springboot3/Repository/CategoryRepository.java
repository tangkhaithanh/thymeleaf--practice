package vn.iostar.demo4_springboot3.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.iostar.demo4_springboot3.Models.Entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Page<CategoryEntity> findByNameContaining(String keyword, Pageable pageable);
}