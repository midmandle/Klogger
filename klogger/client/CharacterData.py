
class CharacterData:

    def __init__(self, character, timeValue):
        self.character = character
        self.time = timeValue

    def getObject(self):
        return [self.character, self.time]
