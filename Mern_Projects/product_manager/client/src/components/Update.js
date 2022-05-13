import React, { useEffect, useState } from 'react'
import axios from 'axios';
import { useHistory, useParams } from "react-router-dom";


const Update = (props) => {
    const { _id } = useParams();
    const [title, setTitle] = useState('');
    const [price, setPrice] = useState('');
    const [description, setDescription] = useState('');
    const [error, setError] = useState({});
    const history = useHistory();


    useEffect(() => {
        axios.get('http://localhost:8000/api/products/' + _id)
            .then(res => {
                setTitle(res.data.title);
                setPrice(res.data.price);
                setDescription(res.data.description);
            })
    }, [_id]);

    const onSubmitHandler = (event) => {
        event.preventDefault();

        axios.patch("http://localhost:8000/api/products/" + _id, {
            title,
            price,
            description
        })
        .then(res=>{
            console.log(res);
            history.push(`/${_id}`);
        })
        .catch(err=>{
            setError(err.response.data.err.errors);
        })
    }

    return(
        <div>
            <form className="w-50 mx-auto" onSubmit={onSubmitHandler}>
                <div className="form-group">
                    <label htmlFor="title">Title:</label>
                    <input type="text" name="title" value={title} className="form-control" onChange={(event) => { setTitle(event.target.value)}}/>
                    <span className="alert-danger">{error.title && error.title.message}</span>
                </div>

                <div className="form-group">
                    <label htmlFor="price">Price:</label>
                    <input type="number" name="price" value={price} className="form-control" onChange={(event) => { setPrice(event.target.value)}}/>
                    <span className="alert-danger">{error.price && error.price.message}</span>
                </div>

                <div className="form-group">
                    <label htmlFor="description">Description:</label>
                    <input type="text" name="description" value={description} className="form-control" onChange={(event) => { setDescription(event.target.value)}}/>
                    <span className="alert-danger">{error.description && error.description.message}</span>
                </div>
                <input type="submit" className="btn btn-info btn-lg mt-3"/>
            </form>
        </div>
    )
}

export default Update;