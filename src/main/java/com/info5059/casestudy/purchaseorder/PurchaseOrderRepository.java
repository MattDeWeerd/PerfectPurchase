package com.info5059.casestudy.purchaseorder;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
// import org.springframework.web.bind.annotation.CrossOrigin;

//@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "products", path = "products")
@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, String> {
    // will return the number of rows deleted
    @Modifying
    @Transactional
    @Query("delete from PurchaseOrder where id = ?1")
    int deleteOne(String poid);

    @Transactional(readOnly = true)
    Optional<PurchaseOrder> findById(long poid);

    @Transactional(readOnly = true)
    List<PurchaseOrder> findByVendorid(Long vendorid);

}
