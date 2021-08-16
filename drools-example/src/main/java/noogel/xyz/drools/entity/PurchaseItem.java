package noogel.xyz.drools.entity;

import lombok.Data;

@Data
public class PurchaseItem {
    private String skuId;
    private Integer quantity;
    private Integer price;
}
