package com.khangnlg.repositories;

import com.khangnlg.entities.CategoryClothesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryClothesRepository extends JpaRepository<CategoryClothesEntity, Long> {
}
