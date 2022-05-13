const { findAll, findOne, createProduct, deleteOne, updateOne} = require("../controllers/product.controller")

module.exports = app => {
    app.route("/api/products").get(findAll).post(createProduct)
    app.route("/api/products/:_id").get(findOne).patch(updateOne).delete(deleteOne)
}