import json

class PackageData:

    def __init__(self, characters, start, end):
        self.values = characters
        self.start = start
        self.end = end

    def packageData_JSON(self):
        tempDict = {}
        tempDict['startTime'] = self.start
        tempDict['endTime'] = self.end
        tempDict['data'] = self.values
        return json.dumps(tempDict)
