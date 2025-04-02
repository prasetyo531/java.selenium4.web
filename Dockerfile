FROM bellsoft/liberica-openjdk-alpine:23

# Install curl jq
RUN apk add curl jq

# workspace
WORKDIR /home/selenium4web-docker

# Add the required files
ADD target    ./