const Product = require("../models/product.model");


const findOne = (req,res) => {
    Product.findOne({_id: req.params._id})
        .then(results => res.json(results))
        .catch(err => res.status(400).json({message: "that didnt quite work", err}))
}

const deleteOne = (req,res) => {
    Product.deleteOne({_id: req.params._id})
        .then(results => res.json(results))
        .catch(err => res.status(400).json({message: "that didnt quite work", err}))
    }

const findAll = (req,res) => {
    console.log(req.body)
        Product.find({})
        .then(results => res.json(results))
        .catch(err => res.status(400).json({message: "that didnt quite work", err}))
    }

const createProduct = (req,res) => {
    Product.create(req.body)
        .then(newProduct => res.json(newProduct))
        .catch(err => res.status(400).json({message: "that didnt quite work", err}))
    }

const updateOne = (req,res) => {
        Product.updateOne({_id: req.params._id}, req.body, {runValidators:true})
            .then(results => res.json(results))
            .catch(err => res.status(400).json({message: "that didnt quite work", err}))
    }

    module.exports = {
        findOne,
        findAll,
        deleteOne,
        createProduct,
        updateOne,
    }