package com.wjs.qb.domain.accusation;

import com.wjs.common.base.base.BaseEntity;
import com.wjs.qb.domain.daccusation.CommentAccusation;
import com.wjs.qb.domain.daccusation.DynamicAccusation;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

@Setter
@Getter
public class Accusation extends BaseEntity {

    private String content;
    private Set<DynamicAccusation> dynamicAccusations = new HashSet<>();
    private Set<CommentAccusation> commentAccusations = new HashSet<>();

    public Accusation() {
    }

    public Set<DynamicAccusation> getDynamicAccusations() {
        return unmodifiableSet(dynamicAccusations);
    }
}
