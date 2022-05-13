const mongoose = require("mongoose");

const db_name = "musics_db";

mongoose.connect(`mongodb://localhost/${db_name}`, {
// mongoose.connect(`mongodb+srv://admin:admin@cluster0.grozb.mongodb.net/myFirstDatabase?retryWrites=true&w=majority`, {
    useNewUrlParser: true,
    useUnifiedTopology: true
})
    .then(()=>console.log("you're connected to the database"))
    .catch(err=>console.log("you're not connected to the database", err))