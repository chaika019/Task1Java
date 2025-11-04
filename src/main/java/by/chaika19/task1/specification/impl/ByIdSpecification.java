package by.chaika19.task1.specification.impl;

import by.chaika19.task1.entity.CustomArray;
import by.chaika19.task1.specification.CustomArraySpecification;

public class ByIdSpecification implements CustomArraySpecification {
    private final String targetId ;

    public ByIdSpecification(String targetId) {
        this.targetId = targetId;
    }

    @Override
    public boolean specify(CustomArray array) {
        return array.getId().equals(targetId );
    }
}
