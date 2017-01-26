import threading

output = []
def worker1():
	for i in range(0,100):
		output.append("a")
		print "a"
def worker2():
	for i in range(0,100):
		output.append("b")
		print "b"

t1 = threading.Thread(target = worker1)
t2 = threading.Thread(target = worker2)

threads = []

threads.append(t1)
threads.append(t2)

for i in threads:
	i.start()

As = 0
Bs = 0
for i in output:
	if(i == "a"):
		As += 1
	if(i == "b"):
		Bs += 1
print output
print As
print Bs
