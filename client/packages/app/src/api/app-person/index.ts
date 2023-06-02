import { request } from '../basic'

// 获取热搜列表
export const reqElvenTypes = () => request({ url: '/getElvenKindList', method: 'get' })

// 上传
export function reqPublishElvenInfo<T>(data: T) {
  return request({
    url: '/addElven',
    data,
    method: 'post',
  })
}
