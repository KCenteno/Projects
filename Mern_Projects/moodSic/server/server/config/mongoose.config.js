const mongoose = require("mongoose");

const db_name = "musics_db";

mongoose.connect(`mongodb://localhost/${db_name}`, {
    useNewUrlParser: true,
    useUnifiedTopology: true
})
    .then(()=>console.log("you're connected to the database"))
    .catch(err=>console.log("you're not connected to the database", err))
