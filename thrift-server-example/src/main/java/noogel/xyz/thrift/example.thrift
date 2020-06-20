namespace java noogel.xyz.thrift

struct SharedStruct {
  1: required string key
  2: required string value
}

service SharedService {
  SharedStruct test(1: required string key)
}