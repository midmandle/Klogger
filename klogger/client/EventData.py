
class EventData:

    def __init__(self, event, timeValue):
        self.event = event.__dict__
        self.time = timeValue

    def getObject(self):
        return [self.event, self.time]
