from flask_app import app
from flask import render_template, redirect, request, session, flash

from flask_app.models.show import Show
from flask_app.models.user import User


@app.route("/new_show")
def new_show():
    if "user_id" not in session:
        flash("please login/ register before entering the site!")
        return redirect("/")

    data = {
        "user_id" : session["user_id"]
    }

    user = User.by_id(data)
    return render_template("new_show.html", user = user)


@app.route("/create_show", methods=["POST"])
def create_show():
    data = {
        "name" : request.form["name"],
        "network" : request.form["network"],
        "date" : request.form["date"],
        "description" : request.form["description"],
        "user_id" : session["user_id"]
    }

    if not Show.validate_show(data):
        return redirect("/new_show")

    Show.create_show(data)
    return redirect("/dashboard")


@app.route("/show/<int:show_id>")
def see_show(show_id):
    if "user_id" not in session:
        flash("please login/ register before entering the site!")
        return redirect("/")

    data = {
        "show_id" : show_id
    }

    show = Show.get_show_with_user(data)
    return render_template("see_show.html", show = show)


@app.route("/show/<int:show_id>/edit")
def edit_show(show_id):
    data = {
        "show_id" : show_id
    }
    show = Show.get_show_with_user(data)
    return render_template("edit_show.html", show = show)


@app.route("/show/<int:show_id>/update", methods=["POST"])
def update_show(show_id):
    data = {
        "name" : request.form["name"],
        "network" : request.form["network"],
        "date" : request.form["date"],
        "description" : request.form["description"],
        "show_id" : show_id
    }

    if not Show.validate_show(data):
        return redirect(f"/show/{show_id}/edit")

    Show.update_show_info(data)
    return redirect("/dashboard")


@app.route("/show/<int:show_id>/delete")
def delete_show(show_id):
    data = {
        "show_id" : show_id
    }

    Show.delete_show(data)
    return redirect("/dashboard")