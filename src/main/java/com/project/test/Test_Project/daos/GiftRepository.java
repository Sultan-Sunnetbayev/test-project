package com.project.test.Test_Project.daos;

import com.project.test.Test_Project.models.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiftRepository extends JpaRepository<Gift, Integer> {

    @Query("SELECT gift FROM Gift gift WHERE gift.id = :giftId")
    Gift findGiftById(@Param("giftId")int giftId);

    @Query("SELECT gift FROM Gift gift WHERE gift.user.id = :userId ORDER BY gift.id")
    List<Gift> findGiftsByUser_Id(@Param("userId")int userId);

    @Query("SELECT gift FROM Gift gift WHERE gift.gift.id = :giftId")
    List<Gift>findGiftsByGift_Id(@Param("giftId")int giftId);

}
