from analysis import characters_per_minute

class packaging:

    def __init__(self, characters, start, end):
        self.characters = characters
        self.start = start
        self.end = end
        self.cpm = characters_per_minute(self.characters, start, end)

    def packageData_JSON():
        return json_data

    
