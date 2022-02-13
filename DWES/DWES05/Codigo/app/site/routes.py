from flask import abort, redirect, render_template, url_for
from . import site_bp

@site_bp.route("/")
def index():
    return redirect(url_for('auth.login'))