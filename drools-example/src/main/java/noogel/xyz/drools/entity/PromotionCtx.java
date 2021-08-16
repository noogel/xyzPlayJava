package noogel.xyz.drools.entity;

import lombok.Data;

import java.util.List;

@Data
public class PromotionCtx {
    private List<PurchaseItem> purchaseItemList;
    private PromotionResult promotionResult;

    public Integer sumAmount() {
        return purchaseItemList.stream().mapToInt(t -> t.getPrice() * t.getQuantity()).sum();
    }

    public Integer sumQuantity() {
        return purchaseItemList.stream().mapToInt(PurchaseItem::getQuantity).sum();
    }
}
