#!/bin/bash

# check_selenium_health.sh

MAX_RETRIES=10
WAIT_TIME=10 # seconds
HUB_URL="http://localhost:4444/wd/hub/status"

for ((i=1; i<=MAX_RETRIES; i++)); do
    RESPONSE=$(curl -s -o /dev/null -w '%{http_code}' "$HUB_URL")
    if [ "$RESPONSE" -eq 200 ]; then
        echo "Selenium Hub is ready!"
        exit 0
    fi
    echo "Waiting for Selenium Hub to be ready... (Attempt $i/$MAX_RETRIES)"
    sleep $WAIT_TIME
done

echo "Selenium Hub did not become ready in time."
exit 1