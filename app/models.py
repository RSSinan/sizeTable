from flask_sqlalchemy import SQLAlchemy

from app import db


class Order(db.Model):
    __tablename__ = "OrderTable"
    id = db.Column("ID", db.Integer, primary_key=True, autoincrement=True)
    order_code = db.Column("OrderCode", db.String(50), nullable=False)
    model_name = db.Column("ModelName", db.String(50), nullable=False)

    def __init__(self, order_code, model_name):
        self.order_code = order_code
        self.model_name = model_name

    def save(self):
        # inject self into db session
        db.session.add(self)

        # commit change and save the object
        db.session.commit()

        return self


class Step(db.Model):
    __tablename__ = "StepTable"
    id = db.Column("ID", db.Integer, primary_key=True, autoincrement=True)
    step_name = db.Column("StepName", db.String(), nullable=False)
    normal_measure = db.Column("NormalMeasure", db.Float())
    instant_measure = db.Column("InstantMeasure", db.Float())
    order_id = db.Column(db.Integer, db.ForeignKey("OrderTable.ID"))

    def __init__(self, step_name, normal_measure, order_id):
        self.step_name = step_name
        self.normal_measure = normal_measure
        self.order_id = order_id

    def save(self):
        db.session.add(self)
        db.session.commit()
