from werkzeug.security import generate_password_hash, check_password_hash
password1 = '123456'
password2 = '56789'
password3 = 'asukita'
print(f'{password1} - {generate_password_hash(password1)}')
print(f'{password2} - {generate_password_hash(password2)}')
print(f'{password3} - {generate_password_hash(password3)}')
