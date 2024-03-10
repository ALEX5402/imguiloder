url="https://anogs.tech/aviwa/connect"
accept_header="Accept: application/json"
content_type_header="Content-Type: application/x-www-form-urlencoded"
charset_header="Charset: UTF-8"

while true; do
    user_key="UrIN7K6RVatG"  # Replace with your actual user key
    android_id="androiddevice"  # Replace with the command to get Android ID
    device_model="testmodel"  # Replace with the command to get device model
    device_brand="testbrand" # Replace with the command to get device brand
    agent="User-Agent: Dalvik Hajajndbhaiakwn"

    hwid="${user_key}${android_id}${device_model}${device_brand}"

    # Generate UUID (hash of hwid)
    uuid=$(echo -n "$hwid" | md5sum | awk '{print $1}')

    # Construct POST data
    data="game=PUBG&user_key=${user_key}&serial=${uuid}"

    # Make cURL request
    response=$(curl -X POST -H "$accept_header" -H "$content_type_header" -H "$charset_header" -H "$agent" -d "$data" "$url")

    echo "$response" > response.txt

    # Process the response
    if [ $? -eq 0 ]; then
        # cURL request succeeded
        status=$(echo "$response" | jq -r '.status')
        if [ "$status" == "true" ]; then
            # Authentication successful
            token=$(echo "$response" | jq -r '.data.token')
            rng=$(echo "$response" | jq -r '.data.rng')
            exp=$(echo "$response" | jq -r '.data.EXP')
            enc=$(echo "$response" | jq -r '.data.Enc')

            current_time=$(date +%s)
            if [ $((rng + 30)) -gt $current_time ]; then
                # Authentication valid
                auth="PUBG-${user_key}-${uuid}-Vm8Lk7Uj2JmsjCPVPVjrLa7zgfx3uz9E"
                output_auth=$(echo -n "$auth" | md5sum | awk '{print $1}')

                # Do something with the token, exp, and enc here
                echo "Authentication successful"
            fi
        else
            # Authentication failed, handle reason
            reason=$(echo "$response" | jq -r '.reason')
            echo "Authentication failed. Reason: $reason"
        fi
    else
        # cURL request failed
        echo "cURL request failed"
    fi

    sleep 1  # Wait for 1 second before the next iteration
done
