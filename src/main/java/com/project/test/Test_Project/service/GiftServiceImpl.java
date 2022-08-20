package com.project.test.Test_Project.service;

import com.project.test.Test_Project.daos.GiftRepository;
import com.project.test.Test_Project.dtos.GiftDTO;
import com.project.test.Test_Project.models.Gift;
import com.project.test.Test_Project.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class GiftServiceImpl implements GiftService{

    private final GiftRepository giftRepository;

    @Autowired
    public GiftServiceImpl(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    @Override
    @Transactional
    public void addGift(final Gift gift, final Gift presentGift, final User user) {

        Gift savedGift = Gift.builder()
                .name(gift.getName())
                .build();
        if(user!=null){
            savedGift.setUser(user);
        }
        if(presentGift!=null){

            savedGift.setGift(presentGift);
        }
        giftRepository.save(savedGift);

        return;
    }

    @Override
    @Transactional
    public void removeGiftById(final int giftId){

        if(giftRepository.findGiftById(giftId)!=null){

            giftRepository.deleteById(giftId);
        }

        return;
    }

    @Override
    public List<GiftDTO> getGiftsByUserId(final int userId){

        List<Gift>gifts=giftRepository.findGiftsByUser_Id(userId);
        if(gifts==null){

            return null;
        }
        List<GiftDTO>giftDTOS=new ArrayList<>();

        for(Gift gift:gifts){

            GiftDTO giftDTO=toDTO(gift);

            giftDTO=getGiftsInGift(gift,giftDTO);
            giftDTOS.add(giftDTO);
        }

        if(giftDTOS.isEmpty()){

            return null;
        }

        return giftDTOS;
    }

    private GiftDTO toDTO(Gift gift){

        GiftDTO giftDTO= GiftDTO.builder()
                .id(gift.getId())
                .name(gift.getName())
                .build();

        return giftDTO;
    }

    private GiftDTO getGiftsInGift(Gift presentGift, GiftDTO giftDTO){

        List<Gift>gifts=giftRepository.findGiftsByGift_Id(presentGift.getId());

        if(gifts==null){

            return giftDTO;
        }
        for(Gift gift:gifts){

            List<GiftDTO>giftDTOS=giftDTO.getGifts();

            if(giftDTOS==null){
                giftDTOS=new ArrayList<>();
            }
            giftDTOS.add(toDTO(gift));
            giftDTO.setGifts(giftDTOS);
            getGiftsInGift(gift, giftDTO.getGifts().get(giftDTOS.size()-1));
        }

        return giftDTO;
    }

    @Override
    public Gift getGiftById(final int giftId){

        return giftRepository.findGiftById(giftId);
    }

    @Override
    public boolean isGiftExistsById(final int giftId){

        if(giftRepository.findGiftById(giftId)!=null){

            return true;
        }else{

            return false;
        }
    }

}
