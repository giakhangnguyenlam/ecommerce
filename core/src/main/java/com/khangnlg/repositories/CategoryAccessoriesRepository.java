package com.khangnlg.repositories;

import com.khangnlg.entities.CategoryAccessoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryAccessoriesRepository extends JpaRepository<CategoryAccessoriesEntity, Long> {
}
