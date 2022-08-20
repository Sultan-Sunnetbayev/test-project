package com.project.test.Test_Project.service;

import com.project.test.Test_Project.dtos.GiftDTO;
import com.project.test.Test_Project.models.Gift;
import com.project.test.Test_Project.models.User;

import javax.transaction.Transactional;
import java.util.List;

public interface GiftService {

    @Transactional
    void addGift(Gift gift, Gift presentGift, User user);

    @Transactional
    void removeGiftById(int giftId);

    List<GiftDTO> getGiftsByUserId(int userId);

    Gift getGiftById(int giftId);

    boolean isGiftExistsById(int giftId);
}
