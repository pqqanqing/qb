package com.wjs.qb.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class MoneyDTO implements Serializable {

    private List<GroupDTO> groupList = new ArrayList<GroupDTO>();
    private List<TopicDTO> topicList = new ArrayList<TopicDTO>();
    private List<DynamicDTO> dynamicList = new ArrayList<DynamicDTO>();

    public MoneyDTO() {
    }

}
