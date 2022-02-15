from flask_wtf import FlaskForm
from wtforms import StringField, SubmitField, PasswordField, EmailField
from wtforms.validators import DataRequired, Email, Length, ValidationError, Regexp

# Expresiones regulares por segmentos:
# Valida numeros de tipo [123456789]: ^\d{9}$
# Valida numeros de tipo [(123)123456789]: ^\(\d{1,3}\)\d{9}$
# Valida numeros de tipo [+(123)123456789]: ^\+\(\d{1,3}\)\d{9}$
# Valida numeros de tipo [+123 123456789]: ^\+\d{1,3}[-\.\s]\d{9}$
# Valida numeros de tipo [(123) 123456789]: ^\(\d{1,3}\)[-\.\s]\d{9}$
# Valida numeros de tipo [+(123) 123456789]: ^\+\(\d{1,3}\)[-\.\s]\d{9}$
# Valida numeros de tipo [123 456 789]: ^\d{3}([-\.\s]\d{3}){2}
# Valida numeros de tipo [+123 123 456 789]: ^\+\d{1,3}([-\.\s]\d{3}){3}$
# Valida numeros de tipo [+(123)123 456 789]: ^\+\(\d{1,3}\)\d{3}([-\.\s]\d{3}){2}$
# Valida numeros de tipo [(123) 123 456 789]: ^\(\d{1,3}\)([-\.\s]\d{3}){3}$
# Valida numeros de tipo [+(123) 123 456 789]: ^\+\(\d{1,3}\)([-\.\s]\d{3}){3}$
phone_regex = '^\d{9}$|^\(\d{1,3}\)\d{9}$|^\+\(\d{1,3}\)\d{9}$|^\+\d{1,3}[-\.\s]\d{9}$|^\(\d{1,3}\)[-\.\s]\d{9}$|^\+\(\d{1,3}\)[-\.\s]\d{9}$|^\d{3}([-\.\s]\d{3}){2}|^\+\d{1,3}([-\.\s]\d{3}){3}$|^\+\(\d{1,3}\)\d{3}([-\.\s]\d{3}){2}$|^\(\d{1,3}\)([-\.\s]\d{3}){3}$|^\+\(\d{1,3}\)([-\.\s]\d{3}){3}$'

# Valida numeros de tipo [(123)123456789]: ^(\()(\d{1,3})(\))(\d{9})$
# Valida numeros de tipo [+(123)123456789]: ^(\+)(\()(\d{1,3})(\))(\d{9})$
# Valida numeros de tipo [+123 123456789]: ^(\+)(\d{1,3})??[-\.\s]\d{9}$
# Valida numeros de tipo [(123) 123456789]: ^(\()(\d{1,3})(\))([-\.\s])(\d{9})$
# Valida numeros de tipo [+(123) 123456789]: ^(\+)(\()(\d{1,3})(\))([-\.\s])(\d{9})$
# Valida numeros de tipo [123 456 789]: ^([-\.\s]\d{3}){3}$
# Valida numeros de tipo [+123 123 456 789]: ^(\+)(\d{1,3})??([-\.\s]\d{3}){3}$
# Valida numeros de tipo [+(123)123 456 789]: ^(\+)(\()(\d{1,3})(\))(\d{3})??([-\.\s]\d{3}){2}$
# Valida numeros de tipo [(123) 123 456 789]: ^(\()(\d{1,3})(\))??([-\.\s]\d{3}){3}$
# Valida numeros de tipo [+(123) 123 456 789]: ^(\+)(\()(\d{1,3})(\))??([-\.\s]\d{3}){3}$
# phone_regex = '^(\d{9})$|^(\()(\d{1,3})(\))(\d{9})$|^(\+)(\()(\d{1,3})(\))(\d{9})$|^(\+)(\d{1,3})??[-\.\s]\d{9}$|^(\()(\d{1,3})(\))([-\.\s])(\d{9})$|^(\+)(\()(\d{1,3})(\))([-\.\s])(\d{9})$|^([-\.\s]\d{3}){3}$|^(\+)(\d{1,3})??([-\.\s]\d{3}){3}$|^(\+)(\()(\d{1,3})(\))(\d{3})??([-\.\s]\d{3}){2}$|^(\()(\d{1,3})(\))??([-\.\s]\d{3}){3}$|^(\+)(\()(\d{1,3})(\))??([-\.\s]\d{3}){3}$'

def check_username(form, field):
    if len(field.data) < 5:
        raise ValidationError('Ha de tenir més de 5 caracters')
    excluded_chars = " *?!'^+%&/()=}][{$#"
    for char in field.data:
        if char in excluded_chars:
            raise ValidationError(f"El caràcter {char} no és vàlid.")

def check_phone(form, field):
    min_length = 9
    if len(field.data) < min_length:
        raise ValidationError(f"Ha de tenir més de {min_length} caracters")
    excluded_chars = "*?!'^%&/=}][{$#"
    for char in field.data:
        if char in excluded_chars:
            raise ValidationError(f"El caràcter {char} no és vàlid.")

class LoginForm(FlaskForm):
    username = StringField('Usuario', validators=[DataRequired(), Length(max=15)])
    password = PasswordField('Password', validators=[DataRequired()])
    submit = SubmitField('Iniciar Sesión')

class SignupForm(FlaskForm):
    username = StringField('Usuario', validators=[DataRequired(),check_username, Length(min=6, max=15)])
    name = StringField('Nom', validators=[DataRequired(), Length(max=50)])
    lastname = StringField('Llinatges', validators=[DataRequired(), Length(max=50)])
    email = EmailField('Email', validators=[DataRequired(), Email()])
    phone = StringField('Telefon', validators=[DataRequired(), Regexp(phone_regex, message='El telefono no tiene el formato correcto.'), Length(max=18)])
    password = PasswordField('Password', validators=[DataRequired()])
    password_check = PasswordField('Confirma Password', validators=[DataRequired()])
    submit = SubmitField('Signup')