package com.example.demo.service;

import com.example.demo.domain.InhousePart;
import com.example.demo.repositories.InhousePartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 *
 *
 *
 *
 */
@Service
public class InhousePartServiceImpl implements InhousePartService {

    @Autowired
    private InhousePartRepository partRepository;

    @Autowired
    public InhousePartServiceImpl(InhousePartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Override
    public InhousePart findById(Long theId) {
        return partRepository.findById(theId).orElse(null);
    }

    @Override
    public void save(InhousePart thePart) {
        partRepository.save(thePart);
    }

    @Override
    public void deleteById(Long theId) {
        partRepository.deleteById(theId);
    }

    @Override
    public List<InhousePart> findAll() {
        return partRepository.findAll();
    }

}
