package com.quad.ws.shop.web.daos;

import com.quad.ws.shop.web.daos.repositoy.ProductBaseRepository;
import com.quad.ws.shop.web.pojo.HandMade;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface HandMadeDao extends ProductBaseRepository<HandMade> {
}
