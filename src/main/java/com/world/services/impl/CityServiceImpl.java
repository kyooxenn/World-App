package com.world.services.impl;



import com.world.entity.City;
import com.world.repository.CityRepository;
import com.world.request.BaseRequest;
import com.world.services.iface.CityService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public Page<City> getAllCity(BaseRequest request) {
       Pageable pageHelper = PageRequest.of(request.getPageNum(), request.getPageSize());
       Page<City> allProducts = (Page<City>) cityRepository.findAll(pageHelper);
       return allProducts;
    }
}
