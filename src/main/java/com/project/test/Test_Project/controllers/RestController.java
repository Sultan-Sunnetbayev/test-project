package com.project.test.Test_Project.controllers;

import com.project.test.Test_Project.dtos.GiftDTO;
import com.project.test.Test_Project.dtos.UserDTO;
import com.project.test.Test_Project.helper.ResponseTransfer;
import com.project.test.Test_Project.models.Gift;
import com.project.test.Test_Project.models.User;
import com.project.test.Test_Project.service.GiftService;
import com.project.test.Test_Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    private final UserService userService;
    private final GiftService giftService;

    @Autowired
    public RestController(UserService userService, GiftService giftService) {
        this.userService = userService;
        this.giftService = giftService;
    }

    @PostMapping(path = "/user/add", produces = "application/json")
    public ResponseEntity addUser(final @ModelAttribute User user){

        Map<String,Object> response=new HashMap<>();

        if(userService.isUserExists(user)){

            response.put("success",false);
            response.put("message","error this email already exists");

            return ResponseEntity.ok(response);
        }
        userService.addUser(user);
        if(userService.isUserExists(user)){

            response.put("success",true);
            response.put("message","accept user successful added");
        }else{

            response.put("success",false);
            response.put("message","error user don't added");
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/gift/add", produces = "application/json")
    public ResponseEntity addGift(final @ModelAttribute Gift gift,
                                  final @RequestParam(value = "giftId", required = false)Integer giftId,
                                  final @RequestParam(value = "userId", required = false)Integer userId){

        Map<String,Object>response=new HashMap<>();

        if(giftId==null && userId==null){

            response.put("success",false);
            response.put("message","error gift must be conferment to user or other gift");
        }
        Gift presentGift=null;
        User user=null;

        if(giftId!=null){
            presentGift=giftService.getGiftById(giftId);
        }
        if(userId!=null){
            user=userService.getUserById(userId);
        }
        if(user==null && presentGift==null){

            response.put("success",false);
            response.put("message","error gift's conferment user or gift not found with this ids");

            return ResponseEntity.ok(response);
        }
        giftService.addGift(gift, presentGift, user);
        response.put("success",true);
        response.put("message","accept gift successful added");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/gift/remove/{giftId}",produces = "application/json")
    public ResponseEntity removeGiftById(final @PathVariable("giftId")int giftId){

        Map<String, Object>response=new HashMap<>();

        if(!giftService.isGiftExistsById(giftId)){

            response.put("success",false);
            response.put("message","error gift not found with this id");

            return ResponseEntity.ok(response);
        }
        giftService.removeGiftById(giftId);
        if(!giftService.isGiftExistsById(giftId)){

            response.put("success",true);
            response.put("message","accept gift successful removed");
        }else{

            response.put("success",false);
            response.put("message","error gift don't removed");
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/user/{userId}",produces = "application/json")
    public ResponseEntity getUserById(final @PathVariable("userId")int userId){

        Map<String,Object>response=new HashMap<>();

        if (!userService.isUserExistsById(userId)) {

            response.put("success",false);
            response.put("message","error user not found with this id");

            return ResponseEntity.ok(response);
        }
        UserDTO userDTO=userService.getUserDTOById(userId);
        List<GiftDTO> giftDTOS=giftService.getGiftsByUserId(userId);

        userDTO.setGifts(giftDTOS);
        ResponseTransfer responseTransfer=new ResponseTransfer(true,new ArrayList<>(
                Arrays.asList(userDTO)));

        return ResponseEntity.ok(responseTransfer);
    }

    @GetMapping(path = "/user/get/all", produces = "application/json")
    public ResponseEntity getAllUsers(){

        List<UserDTO>users=userService.getAllUserDTOS();

        if(users!=null) {
            for (UserDTO userDTO : users) {

                List<GiftDTO> giftDTOS = giftService.getGiftsByUserId(userDTO.getId());

                userDTO.setGifts(giftDTOS);
            }
        }else{

            users=new ArrayList<>();
        }
        ResponseTransfer responseTransfer=new ResponseTransfer(true, users);

        return ResponseEntity.ok(responseTransfer);
    }

}
