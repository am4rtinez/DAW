"""Flask configuration."""
from os import environ, path
from dotenv import load_dotenv

basedir = path.abspath(path.dirname(__file__))
load_dotenv(path.join(basedir, '.env'))

class Config(object):
    """Base config."""
    SECRET_KEY = environ.get('SECRET_KEY')
    DB_USERNAME = environ.get('DB_USERNAME')
    DB_PASSWORD = environ.get('DB_PASSWORD')
    DB_HOST = environ.get('DB_HOST')
    DB_PORT = environ.get('DB_PORT')
    DB_NAME = environ.get('DB_NAME')
    DB_CHARSET = environ.get('DB_CHARSET')

    # SESSION_COOKIE_NAME = environ.get('SESSION_COOKIE_NAME')
    STATIC_FOLDER = 'static'
    TEMPLATES_FOLDER = 'templates'
    SESSION_COOKIE_SECURE =  True


class ProdConfig(Config):
    FLASK_ENV = 'production'
    pass


class DevelopmentConfig(Config):
    DEBUG = True
    TESTING = True
    SESSION_COOKIE_SECURE =  False