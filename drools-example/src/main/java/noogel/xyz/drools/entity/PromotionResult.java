package noogel.xyz.drools.entity;

import lombok.Data;

import java.util.Map;

@Data
public class PromotionResult {
    private Map<String, Integer> item;
}
