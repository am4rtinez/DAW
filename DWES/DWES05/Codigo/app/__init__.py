from flask import Flask
from flask_login import LoginManager

# Create a login manager object
login_manager = LoginManager()

def create_app():
    
    app = Flask(__name__)

    # We can now pass in our app to the login manager
    login_manager.init_app(app)

    # Tell users what view to go to when they need to login.
    login_manager.login_view = "auth.login"

    from .auth import auth_bp
    app.register_blueprint(auth_bp)

    from .site import site_bp
    app.register_blueprint(site_bp)

    return app