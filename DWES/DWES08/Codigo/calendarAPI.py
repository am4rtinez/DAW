from __future__ import print_function

import datetime
import os.path

from google.auth.transport.requests import Request
from google.oauth2.credentials import Credentials
from google_auth_oauthlib.flow import InstalledAppFlow
from googleapiclient.discovery import build
from googleapiclient.errors import HttpError

from database import gimnas

# If modifying these scopes, delete the file token.json.
SCOPES = ['https://www.googleapis.com/auth/calendar']

CREDENTIALS = 'credentials/credentials.json'
TOKEN = 'credentials/token.json'

creds = None

# The file token.json stores the user's access and refresh tokens, and is
# created automatically when the authorization flow completes for the first
# time.
if os.path.exists(TOKEN):
    creds = Credentials.from_authorized_user_file(TOKEN, SCOPES)
# If there are no (valid) credentials available, let the user log in.
if not creds or not creds.valid:
    if creds and creds.expired and creds.refresh_token:
        creds.refresh(Request())
    else:
        flow = InstalledAppFlow.from_client_secrets_file(
            CREDENTIALS, SCOPES)
        creds = flow.run_local_server(port=0)
    # Save the credentials for the next run
    with open(TOKEN, 'w') as token:
        token.write(creds.to_json())

class calendar(object):

  def read_week(day):
    # Obtenemos los dias de la semana.
    monday = datetime.datetime.strptime(day, '%d-%m-%Y')
    # print(monday)
    # Establecemos la hora tope como 23:59:59.9999999
    friday = (monday + datetime.timedelta(days=4)).replace(hour=23, minute=59, second=59, microsecond=999999) 
    
    monday = monday.isoformat() + 'Z'
    friday = friday.isoformat() + 'Z'

    lista = []
    # print(monday)
    # print(friday)

    try:
      service = build('calendar', 'v3', credentials=creds)

      # Call the Calendar API
      #Obtiene los eventos de la semana
      events = service.events().list(calendarId='primary', timeMin=monday, timeMax=friday, singleEvents=True, orderBy='startTime').execute()

      if not events:
        print('No upcoming events found.')
        return

      # Prints the start and name of the next 10 events
      # llistaRes = [{'data': datetime.datetime(2022, 5, 9, 20, 0), 'tipo': 'Coberta', 'nom': 'Angel', 'llinatges': 'Martinez'}]
      for event in events['items']:
        if event['summary'] == 'Reserva pista':
          # print(event['description'])
          dt = datetime.datetime.fromisoformat(event['start']['dateTime'])
          userpista = event['description'].split('-') #Separamos el usuario de la pista.
          nomllin = userpista[0].split() # Obtenemos nombre y apellido
          item = {'data': dt, 'tipo': userpista[1], 'nom': nomllin[0], 'llinatges': nomllin[1]}
          lista.append(item)
          # print(item)
      return lista
        
    except HttpError as error:
      print('An error occurred: %s' % error)
  
  def reservaPista(data, idusuari, idpista):
    # Establecemos fecha y hora de inicio y final para crear el evento.
    dt_start = datetime.datetime.strptime(data, '%Y-%m-%d %H:%M:%S')
    dt_end = dt_start + datetime.timedelta(hours=1)
    dt_start = dt_start.isoformat()
    dt_end = dt_end.isoformat()

    userdata = gimnas.get_data_user(idusuari)
    tipopista = gimnas.get_tipo_pista(idpista)

    user_str = userdata['nom'] + " " + userdata['llinatges']
    event = {
      'summary': 'Reserva pista',
      'location': 'Gimnas DWES',
      'description': user_str + "-" + tipopista["tipo"],
      'start': {
        'dateTime': dt_start,
        'timeZone': 'Europe/Madrid',
      },
      'end': {
        'dateTime': dt_end,
        'timeZone': 'Europe/Madrid',
      },
      "colorId": 5,
      'reminders': {
        'useDefault': False,
        'overrides': [
          {'method': 'popup', 'minutes': 15},
        ],
      },
    }

    try:
      service = build('calendar', 'v3', credentials=creds)
      event = service.events().insert(calendarId='primary', body=event).execute()

    except HttpError as error:
      print('An error occurred: %s' % error)

