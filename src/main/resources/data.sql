INSERT INTO Vendor (Address1,City,Province,PostalCode,Phone,Type,Name,Email)
    VALUES ('660 Oxford St W','London','On', 'N6H 1T8','(519)657-0303','Trusted','Tenderly Toys','tenderly@emailpro.com');
INSERT INTO Vendor (Address1,City,Province,PostalCode,Phone,Type,Name,Email) 
    VALUES ('134 Ottawa N','Hamilton','On', 'L8H 3Z3','(905)547-0730','Trusted','Mirock RC','mirock@protomail.com');
INSERT INTO Vendor (Address1,City,Province,PostalCode,Phone,Type,Name,Email) 
    VALUES ('77 Dundas W','Mississauga','On', 'L5B 1H7','(905)277-5777','Untrusted','Pabco Bricks','pab@co.com');
INSERT INTO Vendor (Address1,City,Province,PostalCode,Phone,Type,Name,Email) 
    VALUES ('23 Marion St','Winnipeg','Mb', 'R2H 0T1','(204)233-0405','Trusted','Matt DeWeerds Hotwheels','md@email.com');

-- add some products to seed the table
INSERT INTO Product (id,vendorid,name,costprice,msrp,rop,eoq,qoh,qoo)
 VALUES ('WaterGunG', 1,'Water Gun - Green', 4.99, 19.99, 7, 5, 15, 3);
INSERT INTO Product (Id,VendorId,Name,CostPrice,Msrp,Rop,Eoq,Qoh,Qoo)
 VALUES ('WaterGunR', 1,'Water Gun - Red', 5.49, 19.99, 7, 5, 12, 5);
INSERT INTO Product (Id,VendorId,Name,CostPrice,Msrp,Rop,Eoq,Qoh,Qoo)
 VALUES ('RCCar01', 2,'Remote Control Car', 7.49, 39.99, 2, 7, 4, 1);
INSERT INTO Product (Id,VendorId,Name,CostPrice,Msrp,Rop,Eoq,Qoh,Qoo)
 VALUES ('LEGO001', 3,'Lego Kit 1', 3.99, 29.99, 5, 3, 6, 0);
INSERT INTO Product (Id,VendorId,Name,CostPrice,Msrp,Rop,Eoq,Qoh,Qoo)
 VALUES ('LEGO002', 3,'Lego Kit 2', 4.99, 34.99, 4, 6, 5, 2);
INSERT INTO Product (Id,VendorId,Name,CostPrice,Msrp,Rop,Eoq,Qoh,Qoo)
 VALUES ('HW-Mustang', 4,'Hotwheels Mustang', 0.99, 5.99, 2, 5, 2, 0);
INSERT INTO Product (Id,VendorId,Name,CostPrice,Msrp,Rop,Eoq,Qoh,Qoo)
 VALUES ('HW-Honda', 4,'Hotwheels Honda', 1.99, 6.99, 1, 4, 3, 5);
INSERT INTO Product (Id,VendorId,Name,CostPrice,Msrp,Rop,Eoq,Qoh,Qoo)
 VALUES ('HW-Mazda', 4,'Hotwheels Mazda', 1.49, 4.49, 3, 6, 2, 3);
INSERT INTO Product (Id,VendorId,Name,CostPrice,Msrp,Rop,Eoq,Qoh,Qoo)
 VALUES ('HW-Ford', 4,'Hotwheels Ford', 0.49, 3.99, 1, 1, 1, 2);
 INSERT INTO Product (Id,VendorId,Name,CostPrice,Msrp,Rop,Eoq,Qoh,Qoo)
 VALUES ('HW-Lamborghini', 4,'Hotwheels Lamborghini', 3.99, 7.99, 1, 2, 3, 1);
INSERT INTO Product (Id,VendorId,Name,CostPrice,Msrp,Rop,Eoq,Qoh,Qoo)
 VALUES ('HW-Porche', 4,'Hotwheels Porche', 2.99, 6.99, 4, 3, 2, 2);
