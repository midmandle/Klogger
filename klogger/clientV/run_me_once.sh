virtualenv .

source bin/activate

pip install appjar

pip install requests

cd ./pyxhook-master/python-xlib-0.18

python setup.py install

cd ../../

deactivate
