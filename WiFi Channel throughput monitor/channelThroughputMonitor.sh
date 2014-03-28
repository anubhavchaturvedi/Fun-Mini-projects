# USAGE channelThroughputMonitor.sh TIME_INTERVAL MAX_CHANNELS

TIME_INTERVAL=$1
MAX_CHANNEL=$2

ifconfig wlan0 down
iwconfig wlan0 mode monitor
ifconfig wlan0 up

channel=1
RX_bytes=$(ifconfig wlan0 | grep "RX\ bytes" | cut -d ":" -f 2 | cut -d " " -f 1)

while [ 1 -eq 1 ]
do
	iwconfig wlan0 channel $channel;
	sleep $TIME_INTERVAL; 
	RX_bytes_new=$(ifconfig wlan0 | grep "RX\ bytes" | cut -d ":" -f 2 | cut -d " " -f 1)
	#echo $RX_bytes_new;
	throughput=`echo "scale=4; ( $RX_bytes_new - $RX_bytes ) / $TIME_INTERVAL " | bc`
	#echo $throughput
	RX_bytes=$RX_bytes_new;
	echo "$channel $throughput"
	channel=`echo "( $channel % $MAX_CHANNEL ) + 1" | bc`
done