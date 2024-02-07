package com.world.request;

import lombok.Data;

@Data
public class BaseRequest {

    private int pageNum = 0;

    private int pageSize = 20;

}
