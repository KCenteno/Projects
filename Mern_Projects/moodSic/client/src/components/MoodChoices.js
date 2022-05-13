import React from "react";
import { Link } from "react-router-dom";

const MoodChoices = () =>{
    return(
        <div>
            <form>
            <label htmlFor="mood">What is your musical mood?
            <br/> <br />
                <select name="mood">
                    <option value=""></option>
                    <option value="rnb">R&B</option>
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
    )
}
export default MoodChoices;