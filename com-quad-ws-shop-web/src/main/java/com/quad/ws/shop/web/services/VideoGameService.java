package com.quad.ws.shop.web.services;

import com.quad.ws.shop.web.error.exception.DuplicateProductIdException;
import com.quad.ws.shop.web.pojo.VideoGame;

public interface VideoGameService {
    void addVideoGame(VideoGame videoGame) throws DuplicateProductIdException;
}
