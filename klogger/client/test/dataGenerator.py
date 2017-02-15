import random
import csv

def generateRandomData(numberOfItems, low, high):
    listRand = []
    for i in range(0, numberOfItems):
        listRand.append(random.randint(low, high))
    return listRand

def createDataStructure():
    userList = range(0,500)
    #Each user has a week to work on the project
    daysWorkedInAWeek = generateRandomData(500, 1, 7)
    #Each day that a user works they can have 0-* sessions
    #Assuming none do more than two sessions per-day
    sessionsPerDay = generateRandomData(500, 0, 2)
    #For each session a user can work for a given ammount of time 0-* minutes, typically between 30 to 120
    timePerSession = generateRandomData(500, 30, 120)
    #For each session a user can generate a number of actions 1-*, typically between 200 - 1000
    actionsPerSession = generateRandomData(500, 200, 1000)
    #For each session a user can generate a number of key presses 0-*, typically between 200 - 1000
    keysPerSession = generateRandomData(500, 200, 1000)
    #For each session a user can generate a number of mouse clicks 1-*, typically between 300 - 800
    clicksPerSession = generateRandomData(500, 300, 800)
    #For each session a user may use multiple programs 1-*, typically between 1 - 5
    progsPerSession = generateRandomData(500, 1, 5)

    print len(userList)
    print len(daysWorkedInAWeek)
    print len(sessionsPerDay)
    print len(timePerSession)
    print len(keysPerSession)
    print len(clicksPerSession)
    print len(progsPerSession)

    outputList = []
    for i in range(0,500):
        print "{0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}".format(userList[i], daysWorkedInAWeek[i], sessionsPerDay[i], timePerSession[i], actionsPerSession[i], keysPerSession[i], clicksPerSession[i], progsPerSession[i])
        outputList.append([userList[i], daysWorkedInAWeek[i], sessionsPerDay[i], timePerSession[i], actionsPerSession[i], keysPerSession[i], clicksPerSession[i], progsPerSession[i]])
    return outputList

def writeRandomDataToCSV(listOfLists):
    with open("testData.csv", "wb") as f:
        writer = csv.writer(f)
        writer.writerows(listOfLists)
