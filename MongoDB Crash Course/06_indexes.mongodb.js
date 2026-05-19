use('ecommerce')

db.sales.find();

db.sales.getIndexes();

db.sales.createIndex({ quantity: 1 }); // indexes in asc order

db.sales.getIndexes();