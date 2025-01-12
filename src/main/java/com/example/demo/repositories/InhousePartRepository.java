package com.example.demo.repositories;

import com.example.demo.domain.InhousePart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 *
 *
 *
 */
public interface InhousePartRepository extends JpaRepository<InhousePart, Long> {
}
