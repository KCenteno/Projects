from flask_app.config.mysqlconnection import connectToMySQL
from flask import flash
from flask_app import tv

from flask_app.models import user

class Show:
    def __init__(self, data):
        self.id = data["id"]

        self.name = data["name"]
        self.network = data["network"]
        self.date = data["date"]
        self.description = data["description"]
        self.user_id = data["user_id"]

        self.created_at = data["created_at"]
        self.updated_at = data["updated_at"]

        self.user = {}


    @staticmethod
    def validate_show(data):
        is_valid = True
        if len(data["name"]) < 3:
            flash("The Show name needs to be at least 3 characters long")
            is_valid=False
        if len(data["network"]) < 3:
            flash("The Show network needs to be at least 3 characters long")
            is_valid=False
        if len(data["date"]) < 2:
            flash("Show Release Date needs to be picked")
            is_valid=False
        if len(data["description"]) < 3:
            flash("Show Description needs to be at least 3 characters long")
            is_valid=False
        return is_valid


    @classmethod
    def create_show(cls, data):
        query = "INSERT INTO shows (name, network, date, description, user_id, created_at) VALUES (%(name)s, %(network)s, %(date)s, %(description)s, %(user_id)s, NOW());"
        results = connectToMySQL(tv).query_db(query, data)
        return results


    @classmethod
    def get_all(cls):
        query = "SELECT * FROM shows LEFT JOIN users ON shows.user_id = users.id;"
        results = connectToMySQL(tv).query_db(query)

        all_shows = []
        for row in results:
            show = cls(row)
            data = {
                "id" : row["users.id"],
                "first_name" : row["first_name"],
                "last_name" : row["last_name"],
                "email" : row["email"],
                "password" : row["password"],
                "created_at" : row["users.created_at"],
                "updated_at" : row["users.updated_at"]
            }
            show.user = user.User(data)
            all_shows.append(show)
        return all_shows


    @classmethod
    def get_show_with_user(cls, data):
        query = "SELECT * FROM shows LEFT JOIN users ON shows.user_id = users.id WHERE shows.id = %(show_id)s;"
        results = connectToMySQL(tv).query_db(query, data)

        show = cls(results[0])
        _data = {
            "id" : results[0]["users.id"],
            "first_name" : results[0]["first_name"],
            "last_name" : results[0]["last_name"],
            "email" : results[0]["email"],
            "password" : results[0]["password"],
            "created_at" : results[0]["users.created_at"],
            "updated_at" : results[0]["users.updated_at"]
        }
        show.user = user.User(_data)
        return show


    @classmethod
    def update_show_info(cls, data):
        query = "UPDATE shows SET name = %(name)s, network = %(network)s, date = %(date)s, description = %(description)s, updated_at = NOW() WHERE id = %(show_id)s;"
        results = connectToMySQL(tv).query_db(query, data)
        return


    @classmethod
    def delete_show(cls, data):
        query = "DELETE FROM shows WHERE id = %(show_id)s;"
        results = connectToMySQL(tv).query_db(query, data)
        return