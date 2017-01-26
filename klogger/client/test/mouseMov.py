import pyxhook

def main():
    hm = HookManager()
    
    hm.HookMouse()
    hm.MouseAllButtonsDown = hm.printevent
    hm.MouseAllButtonsUp = hm.printevent
    hm.MouseMovement = hm.printevent
    hm.start()
    time.sleep(10)
    hm.cancel()
