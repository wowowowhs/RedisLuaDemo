
--public Number saveToken(String key, String token,  double score, int expires, int size, long currentTime) {
--- 获取key
local key = KEYS[1]

local token = ARGV[1]
local score = ARGV[2]
local expires = ARGV[3]
local size = ARGV[4]
local currentTime = ARGV[5]
print("key = ", key)
print("token = ", token)
print("score = ", score)
print("expires = ", expires)
print("size = ", size)
print("currentTime = ", currentTime)

--- 存储token，设置score
redis.call("zadd", key, score, token)
---设置key的过期时间：单位（秒）
redis.call("expire", key, expires)
-----根据当前时间戳currentTime删除过期的token
--redis.call("ZREMRANGEBYSCORE", key, 0, currentTime)
--
-----如果该用户token超过5个，则删除
--local currentTokenCount = redis.call("zcard", key)
--if currentTokenCount < size then
--
--end
return 1