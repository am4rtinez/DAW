from flask_wtf import FlaskForm
from wtforms import StringField, SubmitField, PasswordField, EmailField, DateField
from wtforms.validators import DataRequired, Email, Length, ValidationError, Regexp, EqualTo
from datetime import date

# Expresiones regulares por segmentos:
# Valida numeros de tipo [123456789]: ^\d{9}$
# Valida numeros de tipo [(123)123456789]: ^\(\d{1,3}\)\d{9}$
# Valida numeros de tipo [123 123456789]: ^(\d{1,3})??[-\.\s]\d{9}$
# Valida numeros de tipo [123 123 456 789]: ^(\d{1,3})??([-\.\s]\d{3}){3}$
# Valida numeros de tipo [+(123)123456789]: ^\+\(\d{1,3}\)\d{9}$
# Valida numeros de tipo [+123 123456789]: ^\+\d{1,3}[-\.\s]\d{9}$
# Valida numeros de tipo [(123) 123456789]: ^\(\d{1,3}\)[-\.\s]\d{9}$
# Valida numeros de tipo [+(123) 123456789]: ^\+\(\d{1,3}\)[-\.\s]\d{9}$
# Valida numeros de tipo [123 456 789]: ^\d{3}([-\.\s]\d{3}){2}
# Valida numeros de tipo [+123 123 456 789]: ^\+\d{1,3}([-\.\s]\d{3}){3}$
# Valida numeros de tipo [+(123)123 456 789]: ^\+\(\d{1,3}\)\d{3}([-\.\s]\d{3}){2}$
# Valida numeros de tipo [(123) 123 456 789]: ^\(\d{1,3}\)([-\.\s]\d{3}){3}$
# Valida numeros de tipo [+(123) 123 456 789]: ^\+\(\d{1,3}\)([-\.\s]\d{3}){3}$
PHONE_REGEX = '^\d{9}$|^\(\d{1,3}\)\d{9}$|^(\d{1,3})??([-\.\s]\d{3}){3}$|^(\d{1,3})??[-\.\s]\d{9}$|^\+\(\d{1,3}\)\d{9}$|^\+\d{1,3}[-\.\s]\d{9}$|^\(\d{1,3}\)[-\.\s]\d{9}$|^\+\(\d{1,3}\)[-\.\s]\d{9}$|^\d{3}([-\.\s]\d{3}){2}|^\+\d{1,3}([-\.\s]\d{3}){3}$|^\+\(\d{1,3}\)\d{3}([-\.\s]\d{3}){2}$|^\(\d{1,3}\)([-\.\s]\d{3}){3}$|^\+\(\d{1,3}\)([-\.\s]\d{3}){3}$'
PASSWORD_REGEX = '^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$'


class LoginForm(FlaskForm):
    username = StringField('Usuario', validators=[DataRequired(), Length(max=15)])
    password = PasswordField('Password', validators=[DataRequired()])
    submit = SubmitField('Iniciar Sesión')

'''
Formulario signup:

Username: Un camp de texte, mínim 6 lletres i màxim 15. No pot contenir paraules ofensives de la següent llista (en pots afegir més) ['caca','pedo','culo','pis','java','php']
Nom: Màxim 50 caràcters
LLinatges: Màxim 50 caràcters
Password: Mínim 8 caràcters, com a mínim ha d'incloure una majúscula, una minúscula, un número i un caràcter especial (ni lletra ni numero)
Password repeat: L'usuari haura de repetir el password i hauran de coincidir.
Fecha Alta: Un camp date, inferior o igual al dia actual i amb format dia/mes/any
Email: Ha de ser un camp correcte de correu electrònic.
Telèfon: Pot ser en format "nacional" o "internacional", per tant ha de permetre números i els caràcters "( )+". També els espais, que eliminarà quan emmagatzemi el número a la base de dades.
'''

class SignupForm(FlaskForm):
    username = StringField('Usuario', validators=[DataRequired(), Length(min=6, max=15)])
    name = StringField('Nom', validators=[DataRequired(), Length(max=50)])
    lastname = StringField('Llinatges', validators=[DataRequired(), Length(max=50)])
    email = EmailField('Email', validators=[DataRequired(), Email(message='Requiere un email válido.'), Length(max=120)])
    phone = StringField('Telefon', validators=[DataRequired(), Regexp(PHONE_REGEX, message='El telefono no tiene un formato válido.'), Length(max=18)])
    fecha = DateField('Fecha Alta', format='%Y-%m-%d', validators=[DataRequired()])
    password = PasswordField('Password', validators=[DataRequired(), Regexp(PASSWORD_REGEX, message='La password no contiene un formato válido.'), Length(min=8)])
    password_check = PasswordField('Password Check', validators=[DataRequired(), EqualTo('password', message='Los campos de contraseña deben ser iguales!')])
    submit = SubmitField('Signup')

    def validate_username(self, username):
        forbidden_words = ['caca', 'culo', 'pedo', 'pis', 'java', 'php', 'mierda', 'puto', 'puta']
        for word in forbidden_words:
            if word in self.username.data:
                raise ValidationError(f"Palabra {word} no está permitida en el campo username.")
    
    def validate_fecha(self, fecha):
        today = date.today()
        if self.fecha.data > today:
            raise ValidationError(f'La fecha no puede ser superior a la de hoy {today.strftime("%d/%m/%Y")}')
