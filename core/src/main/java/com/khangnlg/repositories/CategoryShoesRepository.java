package com.khangnlg.repositories;

import com.khangnlg.entities.CategoryShoesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryShoesRepository extends JpaRepository<CategoryShoesEntity, Long> {
}
