import axios from "axios";
import React, { useEffect, useState } from "react";
import { useHistory, Link, useParams } from "react-router-dom";

const SingleProduct = (props) => {
    const [product, setProduct] = useState({});
    const { _id } = useParams();
    const history = useHistory();

    useEffect(()=> {
        axios.get("http://localhost:8000/api/products/" + _id)
        .then(res=>{
            console.log(res)
            setProduct(res.data);
        })
        .catch(err=>{
            console.log(err.response)
        })
}, [_id])

const onDeleteHandler = () =>{
    axios.delete("http://localhost:8000/api/products/" + _id)
        .then(res=>{
            console.log(res);
            history.push(`/`);
        })
        .catch(err=>
            console.log(err.response))
}


    return(
        <div className='w-75 mx-auto p-5'>
            <h1 className="border-bottom">{product.title}</h1>
            <h2>Price: ${product.price}</h2>
            <h2>Description: {product.description}</h2>

            <Link to={`/`}>Back</Link> | <Link to={`/products/${_id}/update`}>Edit</Link> | <a href="/" onClick={onDeleteHandler}>Delete</a>
        </div>
    )
}

export default SingleProduct;