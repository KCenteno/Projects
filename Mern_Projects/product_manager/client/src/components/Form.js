import React, { useEffect, useState } from "react";
import axios from "axios";
import { useHistory, Link } from 'react-router-dom';

const Form = (props) => {
    const [form, setForm] = useState({
        title: "",
        price: "",
        description: ""
    });

    const [products, setProducts] = useState([]);
    const history = useHistory();
    const [error, setError] = useState({});
    const [update, setUpdate] = useState(false);

    useEffect(()=>{
        axios.get("http://localhost:8000/api/products")
            .then(res=>{
                console.log(res);
                setProducts(res.data);
            })
            .catch(err=>
                console.log(err.response))
    },[update])

    const onChangeHandler = (event) => {
        setForm({
            ...form,
            [event.target.name]: event.target.value
        })
    }

    const onSubmitHandler = (event) => {
        event.preventDefault();

        axios.post("http://localhost:8000/api/products", form)
        .then(res=>{
            console.log(res);
            setUpdate(!update)
            setForm({
                title: "",
                price: "",
                description: ""
            })
            history.push("/");
        })
        .catch(err=>{
            setError(err.response.data.err.errors);
        })
    }


    return(
        <div>
            <h1>Product Manager</h1>
            <form className="w-75 mx-auto border-bottom" onSubmit={onSubmitHandler}>
            <div className="form-group">
                    <label htmlFor="title">Title</label>
                    <input onChange={onChangeHandler} type="text" value={form.title} name="title" className="form-control" />
                    <span className="alert-danger">{error.title && error.title.message}</span>
                </div>

                <div className="form-group">
                    <label htmlFor="price">Price</label>
                    <input onChange={onChangeHandler} type="number" value={form.price} name="price" className="form-control" />
                    <span className="alert-danger">{error.price && error.price.message}</span>
                </div>

                <div className="form-group">
                    <label htmlFor="description">Description</label>
                    <input onChange={onChangeHandler} type="text" value={form.description} name="description" className="form-control" />
                    <span className="alert-danger">{error.description && error.description.message}</span>
                </div>

                <input type="submit" className="btn btn-success btn-lg d-block mx-auto my-3" value="Create"/>
            </form>
            <h1>All Products:</h1>
            <table className="w-75 mx-auto">
                <tbody>
                    {
                        products.map((item,i)=>{
                            return <tr key={i}>
                                <td><Link to={`/${item._id}`}>{item.title}</Link></td>
                            </tr> 
                        })
                    }
                </tbody>
            </table>
            <div>
            </div>
        </div>
    )
}

export default Form;