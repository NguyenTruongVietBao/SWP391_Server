package com.math.mathcha.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicDTO {

    private int topic_id;

    private String title;

    private int number;

    private Boolean is_progress_limited;


}
