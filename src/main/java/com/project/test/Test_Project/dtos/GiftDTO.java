package com.project.test.Test_Project.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GiftDTO {

    private int id;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<GiftDTO>gifts;

    @Override
    public String toString() {
        return "GiftDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gifts='"+ gifts + '\'' +
                '}';
    }
}
