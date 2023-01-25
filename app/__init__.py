from flask import Flask, render_template
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)

app.config.from_object('app.config.Config')

db = SQLAlchemy(app)


@app.route("/")
def home_page():
    return render_template("index.html")


@app.before_request
def initialize_database():
    db.create_all()


from app import views, models
