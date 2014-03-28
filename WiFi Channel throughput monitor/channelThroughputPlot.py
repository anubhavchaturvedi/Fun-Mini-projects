import matplotlib.pyplot as plt

MAX_CHANNELS = 13

plt.ion()
fig=plt.figure()
plt.title("Channel throughput")
plt.xlabel("WiFi Channel")
plt.ylabel("Throughput [bytes/sec]")

channels = range(1, MAX_CHANNELS + 1)
values = [0.0]* (MAX_CHANNELS)

line, = plt.plot(channels, values)
line.axes.set_ylim(0, 100)

while 1:
	record = raw_input()
	key = int(record.split(" ")[0])
	value = float(record.split(" ")[1])
	values[key-1] = value
	line.set_ydata(values)
	print values
	max_value=max(values)
	line.axes.set_ylim(0, max_value + int(0.1 * max_value))
	plt.draw()