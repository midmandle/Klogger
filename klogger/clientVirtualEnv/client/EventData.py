
class EventData:

    def __init__(self, type, event, timeValue):
        self.eventType = type;
        self.event = event.__dict__
        self.time = timeValue

    def getObject(self):
        return [self.eventType, self.event, self.time]
