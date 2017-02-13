import json

class PackageData:

    def __init__(self, events, start, end, id):
        self.values = events
        self.start = start
        self.end = end
        self.id = id

    def packageData_JSON(self):
        tempDict = {}
        tempDict['startTime'] = self.start
        tempDict['endTime'] = self.end
        tempDict['data'] = self.values
        tempDict['id'] = self.id
        return json.dumps(tempDict)
