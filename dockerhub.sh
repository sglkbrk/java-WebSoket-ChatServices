echo "build Start"
docker build -t chat-service:latest --no-cache -f Dockerfile .
echo "build stop"
docker tag chat-service:latest harbor.buraksaglik.fun/chat/chat-service:latest
echo "push start"
docker push harbor.buraksaglik.fun/chat/chat-service:latest
echo "push stop"