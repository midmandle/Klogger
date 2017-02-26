import random
import csv
import numpy as np
import scipy.stats as stats
import pylab as pl

def getOutside3std(data, std, mean):
    values = []
    for i, item in enumerate(data):
        if item < (mean - std) or item > (mean+std) :
            values.append(item)

    return values

def rounder(listOfItems):
    output = []
    for i, item in enumerate(listOfItems):
        output.append(int(item))
    return output

def createNormalDataStructure():
    #Each user has a week to work on the project
    daysWorkedInAWeek = rounder(np.random.normal(4,1,1000))
    #Each day that a user works they can have 0-* sessions
    #Assuming none do more than two sessions per-day
    sessionsPerDay = rounder(np.random.normal(2,0.3,1000))
    #For each session a user can work for a given ammount of time 0-* minutes, typically between 30 to 120
    timePerSession = rounder(np.random.normal(75, 15, 1000))
    #For each session a user can generate a number of actions 1-*, typically between 200 - 1000
    actionsPerSession = rounder(np.random.normal(600, 175, 1000))
    #For each session a user can generate a number of key presses 0-*, typically between 200 - 1000
    keysPerSession = rounder(np.random.normal(600, 175, 1000))
    #For each session a user can generate a number of mouse clicks 1-*, typically between 300 - 800
    clicksPerSession = rounder(np.random.normal(550, 75, 1000))
    #For each session a user may use multiple programs 1-*, typically between 1 - 5
    progsPerSession = rounder(np.random.normal(2.5, 0.75, 1000))
    #Final grade
    grades = rounder(np.random.normal(50,15,1000))

    output = []
    for i in range(0,1000):
        output.append([daysWorkedInAWeek[i], sessionsPerDay[i], timePerSession[i], actionsPerSession[i], keysPerSession[i], clicksPerSession[i], progsPerSession[i], grades[i]])
    
    return output

def pickRandomItem(data):
    return data[random.randint(0,len(data)-1)]
                
def createPlagDataStructure(days, sessions, times, actions, keys, clicks, progs, grades):
    daysOut = []
    sessionsOut = []
    timesOut = []
    actionsOut = []
    keysOut = []
    clicksOut = []
    progsOut = []
    gradesOut = []
    for i in range(0,1000):
        daysOut.append(pickRandomItem(days))
        sessionsOut.append(pickRandomItem(sessions))
        timesOut.append(pickRandomItem(times))
        actionsOut.append(pickRandomItem(actions))
        keysOut.append(pickRandomItem(keys))
        clicksOut.append(pickRandomItem(clicks))
        progsOut.append(pickRandomItem(progs))
        gradesOut.append(pickRandomItem(grades))

    output = []
    for i in range(0,1000):
        output.append([daysOut[i], sessionsOut[i], timesOut[i], actionsOut[i], keysOut[i], clicksOut[i], progsOut[i], gradesOut[i]])

    return output    
                

def writeRandomDataToCSV(listOfNormal):
    with open("testData.csv", "wb") as f:
        writer = csv.writer(f)
        writer.writerows(listOfNormal)
        

def showDistribution(normal):
    days = []
    sessions = []
    times = []
    actions = []
    keys = []
    clicks = []
    progs = []
    grades = []
    for i, item in enumerate(normal):
        days.append(item[0])
        sessions.append(item[1])
        times.append(item[2])
        actions.append(item[3])
        keys.append(item[4])
        clicks.append(item[5])
        progs.append(item[6])
        grades.append(item[7])

    days = sorted(days)
    mean = np.mean(days)
    stdD = np.std(days)
    fit = stats.norm.pdf(days, np.mean(days), np.std(days))
    pl.plot(days,fit,'-o')
    pl.hist(days, normed=True)

    pl.show()

    daysO = getOutside3std(days, np.std(days), np.mean(days))
    sessionsO = getOutside3std(sessions, np.std(sessions), np.mean(sessions))
    timesO = getOutside3std(times, np.std(times), np.mean(times))
    actionsO = getOutside3std(actions, np.std(actions), np.mean(actions))
    keysO = getOutside3std(keys, np.std(keys), np.mean(keys))
    clicksO = getOutside3std(clicks, np.std(clicks), np.mean(clicks))
    progsO = getOutside3std(progs, np.std(progs), np.mean(progs))
    gradesO = getOutside3std(grades, np.std(grades), np.mean(grades))

    return daysO, sessionsO, timesO, actionsO, keysO, clicksO, progsO, gradesO

def stripper(list1, list2):
    for i, item in enumerate(list1):
        similar = 0
        if(item[0] == list2[i][0]):
            similar += 1
        elif(item[1] == list2[i][1]):
            similar += 1
        elif(item[2] == list2[i][2]):
            similar += 1
        elif(item[3] == list2[i][3]):
            similar += 1
        elif(item[4] == list2[i][4]):
            similar += 1
        elif(item[5] == list2[i][5]):
            similar += 1
        elif(item[6] == list2[i][6]):
            similar += 1
        elif(item[7] == list2[i][7]):
            similar += 1

        if similar >= 2:
            item.append("P")
            print item
        else:
            item.append("N")
            
