from __future__ import print_function

import datetime
import os.path

from google.auth.transport.requests import Request
from google.oauth2.credentials import Credentials
from google_auth_oauthlib.flow import InstalledAppFlow
from googleapiclient.discovery import build
from googleapiclient.errors import HttpError

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
          split = event['description'].split('-')
          split2 = split[0].split()
          item = {'data': dt, 'tipo': split[1], 'nom': split2[0], 'llinatges': split2[1]}
          lista.append(item)
          # print(item)
      return lista
        
    except HttpError as error:
      print('An error occurred: %s' % error)
  
  def reservaPista(data, idusuari, idpista):
    # day = datetime.datetime.strptime(data, '%d-%m-%Y %H:%M:%S')
    # day = data.isoformat() + 'Z'
    print(data)

    event = {
      'summary': 'Reserva pista',
      # 'location': '800 Howard St., San Francisco, CA 94103',
      'description': idusuari + "-" + idpista,
      'start': {
        'dateTime': '2022-05-28T09:00:00-07:00',
        'timeZone': 'Europe/Madrid',
      },
      'end': {
        'dateTime': '2022-05-28T17:00:00-07:00',
        'timeZone': 'Europe/Madrid',
      },
      # 'recurrence': [
      #   'RRULE:FREQ=DAILY;COUNT=2'
      # ],
      # 'attendees': [
      #   {'email': 'lpage@example.com'},
      #   {'email': 'sbrin@example.com'},
      # ],
      'reminders': {
        'useDefault': False,
        # 'overrides': [
        #   {'method': 'email', 'minutes': 24 * 60},
        #   {'method': 'popup', 'minutes': 10},
        # ],
      },
    }

    try:
      service = build('calendar', 'v3', credentials=creds)
      event = service.events().insert(calendarId='primary', body=event).execute()

    except HttpError as error:
      print('An error occurred: %s' % error)

