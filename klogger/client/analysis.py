def characters_per_minute(characters, start, end):
    #Function to determine the typing speed.
    print "Determined typing speed as: % cpm", ((len(characters)/(end-start))*60)
