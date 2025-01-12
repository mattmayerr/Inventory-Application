package com.example.demo.service;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;

import java.util.List;

/**
 *
 *
 *
 *
 */
public interface InhousePartService {
    InhousePart findById(Long id);
    void save(InhousePart thePart);
    void deleteById(Long id);
    List<InhousePart> findAll();
}
