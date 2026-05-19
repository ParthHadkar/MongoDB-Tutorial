use('ecommerce');

db.products.find();

db.products.find().pretty();

db.products.find({"name" : "Wireless Mouse"});

db.products.find({ category: "Electronics" });

// Comparison Operators
db.products.find({ price: { $gt: 1000 } }); // greater than 1000


// and
db.products.find({ price: { $gte: 1000, $lte: 50000 } }); // greater than equals

// Logical Operators
db.products.find({ $or: [{ category: "Electronics" }, { stock: { $lt: 50 } }] }); // lesser than

// Projection (Select Specific Fields)
db.products.find({}, { name: 1, price: 1, _id: 0 });

// Sorting and Limiting
db.products.find().sort({ price: -1 }).limit(2);

db.products.find().sort({ price: -1 }).skip(1).limit(1);

