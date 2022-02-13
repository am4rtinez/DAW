from flask import render_template, redirect, url_for, request, session
from flask_login import current_user, login_user, logout_user
from werkzeug.urls import url_parse

from app import login_manager
from . import auth_bp
from .forms import LoginForm, SignupForm

@auth_bp.route('/login', methods=['GET', 'POST'])
def login():
    form = LoginForm()
    if request.method == 'POST':
        pass
    
    return render_template('auth/login_form.html', form=form)

@auth_bp.route('/signup', methods=['GET', 'POST'])
def signup():
    form = SignupForm()
    if request.method == 'POST':
        pass
    if form.validate_on_submit():
        session['usuari'] = form.username.data
        return redirect(url_for("auth.login"))
    return render_template('auth/signup_form.html', form=form)

@login_manager.user_loader
def load_user(user_id):
    return None