import requests

import sys
import os

sys.path.append(os.path.abspath("../pyxhook-master/")) #To allow for the pyxhook library to work

import pyxhook
import time
import parsingTool
import PackageData
import EventData
import json
import threading
from random import randint
import math

running = False
cache = []
startTime = 0
endTime = 0
userID = 0

def garbleKeyData(event):#Breaks with special chars e.g. space, minus, questionmark...
    shifter = randint(0, pow(2,100000))
    event.Ascii =  (event.Ascii+shifter)%256
    event.Key = chr((ord(event.Key)+shifter)%26)
    event.ScanCode = 0;
    print event

def sendData(cache):
    #stringData = PackageData.PackageData(cache, startTime, endTime)
    # Sending Data via JSON below:
    stringData = PackageData.PackageData(cache, startTime, endTime, int(userID)).packageData_JSON()
    headers = {'Content-type': 'application/json', 'Accept': 'text/plain'}
    r = requests.post("http://127.0.0.1:8080/recieveData", data=json.dumps(stringData), headers=headers)
    return r

def cacheData(type, event, time):
    global cache
    cache.append(EventData.EventData(type, event, time).getObject())

#Record Mouse actions.
def moevent (event):
    cacheData("mo", event, time.time())

#Record Keyboard actions.
def kbevent (event):
    cacheData("kb", event, time.time())

def runHook(connection, user):
    hm = pyxhook.HookManager()
    hm.KeyDown = kbevent
    hm.MouseAllButtonsDown = moevent
    hm.HookMouse()
    hm.HookKeyboard()
    hm.start()

    global userID
    userID = user

    global running
    running = True

    global endTime
    global startTime
    startTime = time.time()

    while running:
        running = connection.recv()
        time.sleep(0.1)

    global endTime
    endTime = time.time()

    global cache
    status = sendData(cache)
    connection.send(status)

    hm.cancel()
