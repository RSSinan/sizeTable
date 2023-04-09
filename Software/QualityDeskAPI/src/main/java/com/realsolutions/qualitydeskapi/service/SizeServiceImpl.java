package com.realsolutions.qualitydeskapi.service;

import com.realsolutions.qualitydeskapi.dao.SizeRepository;
import com.realsolutions.qualitydeskapi.entity.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SizeServiceImpl implements SizeService {

    private final SizeRepository sizeRepository;

    @Autowired
    public SizeServiceImpl(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    @Override
    public List<Size> findAll() {
        return sizeRepository.findAll();
    }

    @Override
    public Optional<Size> findById(int id) {
        return sizeRepository.findById(id);
    }

    @Override
    public void update(Size size) {
        sizeRepository.save(size);
    }

    @Override
    public void deleteById(int id) {
        sizeRepository.deleteById(id);
    }

    @Override
    public List<Size> findByName(String name) {
        return sizeRepository.findByName(name);
    }
}
