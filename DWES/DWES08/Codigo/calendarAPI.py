from __future__ import print_function
from asyncio import constants

from datetime import date, timezone
import datetime

import os.path

from google.auth.transport.requests import Request
from google.oauth2.credentials import Credentials
from google_auth_oauthlib.flow import InstalledAppFlow
from googleapiclient.discovery import build
from googleapiclient.errors import HttpError

# If modifying these scopes, delete the file token.json.
SCOPES = ['https://www.googleapis.com/auth/calendar.readonly']

CREDENTIALS = 'credentials/credentials.json'
TOKEN = 'credentials/token.json'

class calendar(object):

    def read_week(day):
        # Obtenemos los dias de la semana.
        monday = datetime.datetime.strptime(day, '%d-%m-%Y')
        # Establecemos la hora tope como 23:59:59.9999999
        friday = (monday + datetime.timedelta(days=4)).replace(hour=23, minute=59, second=59, microsecond=999999) 
        
        monday = monday.isoformat() + 'Z'
        friday = friday.isoformat() + 'Z'

        lista = []
        print(monday)
        print(friday)

        """Shows basic usage of the Google Calendar API.
        Prints the start and name of the next 10 events on the user's calendar.
        """
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

        try:
            service = build('calendar', 'v3', credentials=creds)

            # Call the Calendar API
            print('Getting the week events')

            #Obtiene los eventos de la semana
            events = service.events().list(calendarId='primary', timeMin=monday, timeMax=friday, singleEvents=True, orderBy='startTime').execute()
            # print(events_result)
            # events = events_result.get('items', [])

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
                  print(item)
            return lista
            
        except HttpError as error:
            print('An error occurred: %s' % error)
