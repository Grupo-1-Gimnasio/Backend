package com.inditex.gym_lorza.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalTime;

@Getter
@Setter
public class ActivityResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String price;
    private String weekDay;
    private LocalTime startHour;
    private LocalTime endHour;
    private String image;
    private String trainerName;
}
