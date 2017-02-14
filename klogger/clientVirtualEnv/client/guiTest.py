from appJar import gui
from kloggerClientHTTP import *
from multiprocessing import Process, Pipe

app = gui("Klogger V1.0")
parent_conn, child_conn = Pipe()
p = None

def press(btn):
    global app
    if btn == "Start":
        print app.getNumericEntry("userid")
        global p
        p = Process(target=runHook, args=(child_conn, app.getEntry("userid")))
        p.start()
        app.addFlashLabel("recording", "Recording", 2, 0, 2)
    else:
        global parent_conn
        parent_conn.send(False)
        app.removeLabel("recording")
        app.stop()

def main():

    global app
    app.addLabel("welcome", "Welcome to Klogger", 0, 0, 2)
    app.addLabel("labelInfo1", "Please provide your ID", 1, 0)
    app.addNumericEntry("userid", 1, 1)

    app.addButtons(["Start", "Stop"], press, 3, 0, 2)
    global p

    app.go()

if __name__ == '__main__':
    main()
