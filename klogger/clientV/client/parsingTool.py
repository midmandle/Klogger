import urllib

def filterSpecialChars(characters):
    determineIfSpecialChar = {
        "space": " ",
        "question": "?"
    }

    ret = determineIfSpecialChar.get(characters, characters)
    return ret

def garbleData(characters):
    return 0

def encodeData(characters):
    filteredChars = []
    for i, item in enumerate(characters):
        filteredChars .append(filterSpecialChars(item))

    stringVal = ''.join(filteredChars)
    return urllib.quote_plus(stringVal)

'''
FOR REFERENCE WHEN YOU NEED TO DECODE

        >>> output #Encoded value
    'abc+def'
        >>> final = urllib.unquote_plus(output) #Decoding process
        >>> final
    'abc def' #Output decoded value
'''
