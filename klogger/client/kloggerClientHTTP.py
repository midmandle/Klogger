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
import SimpleHTTPServer, BaseHTTPServer, httplib
import SocketServer

class StoppableHTTPServer(BaseHTTPServer.HTTPServer):

    def server_bind(self):
        BaseHTTPServer.HTTPServer.server_bind(self)
        self.socket.settimeout(1)
        self.run = True

    def get_request(self):
        while self.run:
            try:
                sock, addr = self.socket.accept()
                sock.settimeout(None)
                return (sock, addr)
            except socket.timeout:
                pass

    def stop(self):
        self.run = False

    def serve(self):
        while self.run:
            self.handle_request()

class MyRequestHandler(SimpleHTTPServer.SimpleHTTPRequestHandler):
    def do_GET(self):
        print "PING"
        sendData(cache)
        return 200

running = False
cache = []
startTime = 0
endTime = 0
PORT = 1234
Handler = MyRequestHandler
httpd = StoppableHTTPServer(("localhost", PORT), Handler)

def listenForRequest():
    global PORT
    print "Serving at port", PORT
    httpd.serve()

def sendData(cache):
    stringData = PackageData.PackageData(cache, startTime, endTime)
    # Sending Data via JSON below:
    stringData = PackageData.PackageData(cache, startTime, endTime).packageData_JSON()
    headers = {'Content-type': 'application/json', 'Accept': 'text/plain'}
    r = requests.post("http://127.0.0.1:8080/recieveData", data=json.dumps(stringData), headers=headers)

def cacheData(event, time):
    #Cache data for certain period.
    global cache
    cache.append(EventData.EventData(event, time).getObject())

def kbevent (event):
    cacheData(event, time.time())
    if event.Ascii == 65:
        global running
        running = False
        sendData(cache)
        httpd.stop()

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
    listeningThread = threading.Thread(target=listenForRequest)
    hookThread.start()
    listeningThread.start()


if __name__ == '__main__':
    main()
