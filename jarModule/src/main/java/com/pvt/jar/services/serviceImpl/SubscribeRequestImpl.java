package com.pvt.jar.services.serviceImpl;

import com.pvt.jar.entity.SubscribeRequest;
import com.pvt.jar.entity.User;
import com.pvt.jar.repository.SubscribeRequestRepository;
import com.pvt.jar.services.BaseService;
import com.pvt.jar.services.SubscribeRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubscribeRequestImpl extends BaseService<SubscribeRequest,Long> implements SubscribeRequestService {

    @Autowired
    SubscribeRequestRepository subscribeRequestRepository;

    @Override
    public Page<SubscribeRequest> findByIdChanel(long idChanel, Pageable pageable) {


        return subscribeRequestRepository.findByIdChanel(idChanel,pageable);
    }

    @Transactional
    @Override
    public void add(SubscribeRequest subscribeRequest){

        SubscribeRequest srCheck  = subscribeRequestRepository.findByIdSubscriberAndIdChanel(subscribeRequest.getIdSubscriber(),
                subscribeRequest.getIdChanel()).orElse(null);

        if(srCheck==null){
            subscribeRequestRepository.save(subscribeRequest);
        }
    }
}
