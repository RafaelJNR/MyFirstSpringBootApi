insert into erp.userdata(iduser,username,password,rol) values(nextval('erp.userdata_id_seq'), '7gWwCJ9pQDeRdVjs1DQPaw==', '7gWwCJ9pQDeRdVjs1DQPaw==', 'USER');
insert into erp.userdata(iduser,username,password,rol) values(nextval('erp.userdata_id_seq'), 'aNoFcyQYjZNkqhh5dUErkA==', 'aNoFcyQYjZNkqhh5dUErkA==', 'ADMIN');

insert into erp.supplierdata(idsupplier,name,country) values(nextval('erp.supplier_id_seq'), 'Not Confortable Chairs', 'England');
insert into erp.supplierdata(idsupplier,name,country) values(nextval('erp.supplier_id_seq'), 'Mesas Paco', 'España');

insert into erp.itemdata(iditem,code,price, state, description, username) values(nextval('erp.itemdata_id_seq'),
                                                            'ch1', 100, 'ACTIVE', 'Confortable Chair', 'a');

insert into erp.itemdata(iditem,code,price, state, description, username) values(nextval('erp.itemdata_id_seq'),
                                                            'tb1', 124, 'ACTIVE', 'Big table on black', 'b');

insert into erp.reducedpricedata(idreducedprice,reducedprice, startdate, enddate, itemdata) values(nextval('erp.reducedprice_id_seq'), 85,
                                                            '2023-05-02', '2023-07-05', 1);
insert into erp.reducedpricedata(idreducedprice,reducedprice,startdate, enddate, itemdata) values(nextval('erp.reducedprice_id_seq'), 90,
                                                            '2023-04-10', '2023-06-21', 2);

insert into itemdata_supplierdata(itemdata_id, supplierdata_id) values (1, 1);
insert into itemdata_supplierdata(itemdata_id, supplierdata_id) values (2, 2);



