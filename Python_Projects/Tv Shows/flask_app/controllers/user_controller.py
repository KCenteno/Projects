from flask_app import app
from flask import render_template, redirect, request, session, flash

from flask_app.models.user import User
from flask_app.models.show import Show


from flask_bcrypt import Bcrypt
bcrypt = Bcrypt(app)

@app.route("/")
def index():
    return render_template("index.html")


@app.route("/register", methods=["POST"])
def register():
    data = {
        "first_name" : request.form["first_name"],
        "last_name" : request.form["last_name"],
        "email" : request.form["email"],
        "password" : request.form["password"],
        "pass_con" : request.form["pass_con"]
    }
    if not User.validate_register(data):
        return redirect("/")

    data["password"] = bcrypt.generate_password_hash(request.form["password"])
    new_user_id = User.create_user(data)

    session["user_id"] = new_user_id
    return redirect("/dashboard")


@app.route("/login", methods=["POST"])
def login():
    data = {
        "email" : request.form["email"],
        "password" : request.form["password"]
    }
    if not User.validate_login(data):
        return redirect("/")

    user = User.by_email(data)

    session["user_id"] = user.id
    return redirect("/dashboard")


@app.route("/dashboard")
def dashboard():
    if "user_id" not in session:
        flash("please login/ register before entering the site!")
        return redirect("/")

    data = {
        "user_id" : session["user_id"]
    }
    all_shows = Show.get_all()

    user = User.by_id(data)
    return render_template("dashboard.html", user = user, all_shows = all_shows)


@app.route("/logout")
def logout():
    session.clear()
    flash("successfully logout")
    return redirect("/")