local key = KEYS[1]     --限流KEY
local max = tonumber(ARGV[1])       --限流大小
local timeOut = tonumber(ARGV[2])       --过期时间
local current = redis.call('get', key)  --返回
redis.log(redis.LOG_WARNING, "key=" .. key)
redis.log(redis.LOG_WARNING, "avg=---" .. max, timeOut)
if current then
    if current + 1 > max then
        --如果超出限流大小
        return 0
    else
        redis.call("INCRBY", key, 1)
        return current + 1
    end
else
    redis.call("set", key, "1")
    redis.call("expire", key, timeOut)      --过期时间：单位（秒）
    return 1
end