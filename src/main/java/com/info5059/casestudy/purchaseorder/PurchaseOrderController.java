package com.info5059.casestudy.purchaseorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.info5059.casestudy.product.ProductRepository;

//@CrossOrigin
@RestController
public class PurchaseOrderController {
    @Autowired
    private PurchaseOrderDAO purchaseOrderDAO;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PurchaseOrderRepository poRepository;

    @PostMapping("/api/purchaseorders")
    public ResponseEntity<PurchaseOrder> addOne(@RequestBody PurchaseOrder clientpo) { // use RequestBody here
        return new ResponseEntity<PurchaseOrder>(purchaseOrderDAO.create(clientpo, productRepository), HttpStatus.OK);
    }

    @GetMapping("/api/purchaseorders")
    public ResponseEntity<Iterable<PurchaseOrder>> findAll() {
        Iterable<PurchaseOrder> pos = poRepository.findAll();
        return new ResponseEntity<Iterable<PurchaseOrder>>(pos, HttpStatus.OK);
    }

    @GetMapping("/api/purchaseorders/{id}")
    public ResponseEntity<Iterable<PurchaseOrder>> findByVendor(@PathVariable Long id) {
        return new ResponseEntity<Iterable<PurchaseOrder>>(poRepository.findByVendorid(id), HttpStatus.OK);
    }

}