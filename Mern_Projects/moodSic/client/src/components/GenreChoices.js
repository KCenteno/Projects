import React from "react";
import { Link } from "react-router-dom";

const GenreChoices = () =>{
    return(
        <div>
            <form>
                <label htmlFor="genre">Genre you would like to listen to:
                <br /> <br />
                    <select name="genre">
                        <option value=""></option>
                        <option value="rnb">RB</option>
                        <option value="Hip-Hop">Hip-Hop</option>
                        <option value="Rock">Rock</option>
                        <option value="Pop">Pop</option>
                        <option value="Country">Country</option>
                        <option value="Latin">Latin</option>
                        <option value="Dance/Electronic">Dance/Electronic</option>
                        <option value="Christian/Gospel">Christian/Gospel</option>
                        <option value="World">World</option>
                        <option value="Classical">Classical</option>
                    </select>
                </label>
            </form> <br />
            <Link to="/dashboard" className="btn btn-info">Dashoard</Link>
        </div>
    );
};

export default GenreChoices;