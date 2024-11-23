
--public Number saveToken(String key, String token,  double score, int expires, int size, long currentTime) {
--- 获取key
local key1 = KEYS[1]
local key2 = KEYS[2]
local arg1 = ARGV[1]

print("key1 = ", key1)
print("key2 = ", key2)
print("arg1 = ", arg1)

--- 存储token，设置score
-- redis.call("zadd", key, score, token)
---设置key的过期时间：单位（秒）
-- redis.call("expire", key, expires)

redis.call("set", key1, key2)
return 1