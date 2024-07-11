package com.math.mathcha.repository;

import com.math.mathcha.entity.Category;
import com.math.mathcha.entity.Chapter;
import com.math.mathcha.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
