package com.world.services.iface;

import com.world.entity.City;
import com.world.request.BaseRequest;
import org.springframework.data.domain.Page;


public interface CityService  {
    Page<City> getAllCity(BaseRequest request);

}
