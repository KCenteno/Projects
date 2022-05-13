const mongoose = require("mongoose");

const ProductSchema = new mongoose.Schema({
    title: {
        type: String,
        required: [true, "Title is required"],
        minlength: [3, "Title most be atleast 3 characters"]
    },
    price: {
        type: Number,
        required: [true, "Needs to cost something!"],
        min: [3, "Price has to be more then $3"]
    },
    description: {
        type: String,
        required: [true, "A description is required"],
        minlength: [3, "Description most be atleast 3 characters"]
    }
},{timestamps: true})

const Product = mongoose.model("Product", ProductSchema)

module.exports = Product;