package task1.by.chaika19.specification;

import task1.by.chaika19.entity.CustomArray;

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
