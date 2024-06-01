package com.math.primarySchoolMath.repository.ChapterRepository;

import com.math.primarySchoolMath.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ChapterRepository extends JpaRepository <Chapter,Integer> {
}
