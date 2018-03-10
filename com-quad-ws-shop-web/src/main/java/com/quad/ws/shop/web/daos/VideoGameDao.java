package com.quad.ws.shop.web.daos;

import com.quad.ws.shop.web.daos.repositoy.ProductBaseRepository;
import com.quad.ws.shop.web.pojo.VideoGame;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface VideoGameDao extends ProductBaseRepository<VideoGame> {
}
