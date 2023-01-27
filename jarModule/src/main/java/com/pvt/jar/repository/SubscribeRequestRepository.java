package com.pvt.jar.repository;

import com.pvt.jar.entity.SubscribeRequest;
import com.pvt.jar.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SubscribeRequestRepository extends BaseRepository<SubscribeRequest,Long>{

    Page<SubscribeRequest> findByIdChanel(long idChanel, Pageable pageable);

    Optional<SubscribeRequest> findByIdSubscriberAndIdChanel(long idUser, long idChanel);

}
