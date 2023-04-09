package com.realsolutions.qualitydeskapi.service;


import com.realsolutions.qualitydeskapi.entity.Size;

import java.util.List;
import java.util.Optional;

public interface SizeService {

    public List<Size> findAll();

    public Optional<Size> findById(int id);

    public void update(Size size);

    public void deleteById(int id);

    public List<Size> findByName(String name);
}
