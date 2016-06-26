import pyxhook
import time

def kbevent (event):
    print event

    if event.Ascii == 32:
        global running
        running = False

hm = pyxhook.HookManager()

hm.KeyDown = kbevent

hm.HookKeyboard()

hm.start()

running = True

while running:
    time.sleep(0.1)

hm.cancel()
    
