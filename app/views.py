from flask import redirect, url_for, request, render_template

from app import app
from app.models import Order, Step

current_step_id = -1
current_order_id = 0
current_measurement = 0


@app.route('/index', methods=['GET'])
def index():
    global current_order_id
    global current_step_id
    step_dict = {}
    order_dict_all = {}
    order_dict = {}
    for i in Order.query.filter_by().all():
        order_dict_all[i.id] = [i.order_code, i.model_name]

    order_id = request.args.get("order_id")
    current_step_id = -1
    current_order_id = -1
    if order_id is not None:
        current_order_id = order_id
        step_dict = {}
        for i in Step.query.filter_by(order_id=order_id).all():
            step_dict[i.id] = [i.step_name, i.normal_measure, i.instant_measure]

        order_dict = {}
        for i in Order.query.filter_by(id=order_id).all():
            order_dict[i.id] = [i.order_code, i.model_name]

        return render_template("index.html", order_dict=order_dict, order_dict_all=order_dict_all, step_dict=step_dict,
                               order_id=int(order_id))

    else:
        return render_template("index.html", order_dict=order_dict, order_dict_all=order_dict_all, step_dict=step_dict)


@app.route('/get_current_step_id', methods=['GET'])
def get_current_step_id():
    global current_step_id
    global current_order_id
    null_stat = False
    if current_order_id != 0:
        step_filter = Step.query.filter_by(order_id=current_order_id).all()
        if step_filter:
            for i in step_filter:
                if i.instant_measure is None:
                    null_stat = True
                    current_step_id = i.id
                    break
            if not null_stat:
                current_step_id = -1
        else:
            current_step_id = -1
    return str(current_step_id)


@app.route('/get_current_measurement', methods=['GET'])
def get_current_measurement():
    global current_measurement
    return str(current_measurement)


@app.route('/order_definition', methods=['GET', 'POST'])
def order_definition():
    global current_step_id
    if request.method == 'GET':
        current_step_id = -1
        return render_template("order_definition.html")
    else:
        order_code = request.form["order_code"]
        model_name = request.form["model_name"]
        order_filter = Order.query.filter_by(order_code=order_code).first()
        if order_filter:
            return render_template('order_definition.html', error="order_code_already_defined")
        else:
            Order(order_code=order_code, model_name=model_name).save()
            return render_template("order_definition.html", error="ok")


@app.route('/step_definition', methods=['POST', 'GET'])
def step_definition():
    global current_step_id
    order_dict = {}
    for i in Order.query.filter_by().all():
        order_dict[i.id] = i.order_code
    if request.method == 'POST':
        step_name = request.form['step_name']
        normal_measure = request.form['normal_measure']
        order_id = request.form.get("order_select")
        step_filter = Step.query.filter_by(step_name=step_name, order_id=order_id).first()
        if step_filter:
            return render_template("step_definition..html", order_dict=order_dict, error="step_name_already_defined")
        else:
            step = Step(step_name=step_name, normal_measure=normal_measure, order_id=order_id)
            step.save()
            return render_template("step_definition..html", order_dict=order_dict, error="ok")
    else:
        current_step_id = -1
        return render_template("step_definition..html", order_dict=order_dict)


@app.route('/add_step_measure', methods=['POST'])
def add_step_measure():
    global current_step_id
    global current_measurement
    if current_step_id != 0:
        current_measure = request.args.get('measure')
        step_filter = Step.query.filter_by(id=current_step_id).first()
        if step_filter:
            step_filter.instant_measure = current_measure
            step_filter.save()
            current_measurement = current_measure
        null_stat = False
        next_measurement_value = -1
        step_filter = Step.query.filter_by(order_id=current_order_id).all()
        if step_filter:
            for i in step_filter:
                if i.instant_measure is None:
                    null_stat = True
                    next_measurement_value = i.normal_measure
                    break
            if not null_stat:
                next_measurement_value = -1
        return str(next_measurement_value)
    else:
        return str(-1)


@app.route('/get_normal_measure', methods=['GET'])
def get_normal_measure():
    global current_step_id
    normal_measure = -1
    if current_step_id != 0:
        step_filter = Step.query.filter_by(id=current_step_id).first()
        if step_filter:
            normal_measure = step_filter.normal_measure
        return str(normal_measure)
    else:
        return str(-1)
