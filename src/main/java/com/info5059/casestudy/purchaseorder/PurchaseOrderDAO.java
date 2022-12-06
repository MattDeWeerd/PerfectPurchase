package com.info5059.casestudy.purchaseorder;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.info5059.casestudy.product.Product;
import com.info5059.casestudy.product.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

@Component
public class PurchaseOrderDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public PurchaseOrder create(PurchaseOrder clientpo, ProductRepository prodRepo) {
        PurchaseOrder realPurchaseOrder = new PurchaseOrder();
        realPurchaseOrder.setPodate(LocalDateTime.now());
        realPurchaseOrder.setAmount(clientpo.getAmount());
        realPurchaseOrder.setVendorid(clientpo.getVendorid());
        entityManager.persist(realPurchaseOrder);
        for (PurchaseOrderLineitem item : clientpo.getItems()) {
            PurchaseOrderLineitem realLineitem = new PurchaseOrderLineitem();
            realLineitem.setPoid(realPurchaseOrder.getId());
            realLineitem.setPrice(item.getPrice());
            realLineitem.setProductid(item.getProductid());
            realLineitem.setQty(item.getQty());
            entityManager.persist(realLineitem);

            // we also need to update the QOO on the product table
            Product prod = prodRepo.getReferenceById(item.getProductid());
            prod.setQoo(prod.getQoo() + item.getQty());
            prodRepo.saveAndFlush(prod);

        }

        entityManager.refresh(realPurchaseOrder);

        return realPurchaseOrder;
    }
}
