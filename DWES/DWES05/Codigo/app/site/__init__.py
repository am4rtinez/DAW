from flask import Blueprint
site_bp = Blueprint('site', __name__, template_folder='templates')
from . import routes