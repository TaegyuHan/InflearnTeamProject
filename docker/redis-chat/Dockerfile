# Dockerfile

# redis 이미지를 기반으로 이미지 생성
FROM redis

# 설정 파일
COPY redis.conf /usr/local/etc/redis/redis.conf
CMD [ "redis-server", "/usr/local/etc/redis/redis.conf" ]