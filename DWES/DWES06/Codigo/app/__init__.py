from flask import Flask, Blueprint
from flask_restful import Api

# Api = Api()

def create_app():
   app = Flask(__name__)
   
   from .api import api_bp
   app.register_blueprint(api_bp)
   
   return app