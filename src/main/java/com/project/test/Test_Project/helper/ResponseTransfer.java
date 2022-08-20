package com.project.test.Test_Project.helper;

import com.project.test.Test_Project.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTransfer {

    private Boolean success;
    private List<UserDTO>data;

}
