# Config values
from os import environ, path
from dotenv import load_dotenv

basedir = path.abspath(path.dirname(__file__))
load_dotenv(path.join(basedir, '.env'))

# Database config
db_user = environ.get('DB_USERNAME')
db_password = environ.get('DB_PASSWORD')
db_host = environ.get('DB_HOST')
db_port = environ.get('DB_PORT')
db_name = environ.get('DB_NAME')
db_charset = environ.get('DB_CHARSET')
