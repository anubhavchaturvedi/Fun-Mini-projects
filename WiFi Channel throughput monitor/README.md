### WiFi channel throughput monitor

This code puts the Wireless Network Interface card (WNIC) on monitor mode and checks the throughput at different channels. the user is free to define the time interval between channel hops.

The code consist of a bash script and a python script.
The bash script prints the channel and its throughput on the standard output.
The python script reads the output of bash script and plots it on the graph.

Make sure you run the script as a root user.

## USAGE

bash channelThroughputMonitor.sh TIME_BETWEEN_HOPS NUMBER_OF_CHANNELS_SUPPORTED | python channelThroughputPlot.py

