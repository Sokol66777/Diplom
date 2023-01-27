package com.pvt.jar.services;

import com.pvt.jar.entity.SubscribeRequest;
import com.pvt.jar.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubscribeRequestService extends IService<SubscribeRequest,Long>{

    Page<SubscribeRequest> findByIdChanel(long idChanel, Pageable pageable);
}
