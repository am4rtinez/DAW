from flask import Blueprint
api_bp = Blueprint('api', __name__, url_prefix='/gimnas')
from . import routes