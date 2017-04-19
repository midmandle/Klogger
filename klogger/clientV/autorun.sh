virtualenv . # Setup vitrual environment for loading modules.

source bin/activate # Activate the new virtual environment.

pip install appJar # Install appJar

pip install requests # Install requests (used for network comms).

cd ./pyxhook-master/python-xlib-0.18 # Move to setup location of the xlib lib

python setup.py install # Install the python-xlib-0.18 library

cd ../../ # Go back to root of USB/virtualenv.

./run.sh # Run the gui.

deactivate # Deactivate the virtual environment.

rm -rf bin/ local/ lib/ include/ # Clean up files for next use.
