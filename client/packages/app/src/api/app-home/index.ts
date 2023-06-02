import { request } from '../basic'
import type { IreqElvenList, IreqHotList } from './types'

// 获取热搜列表
export const reqHotList = (data: IreqHotList) => request({ url: '/getElvenHotList', data, method: 'post' })

// 获取精灵动态
export const reqElvenList = (params: IreqElvenList) => request({ url: '/getElvenList', params, method: 'get' })

// 点赞
export const reqToLike = (data: { elvesId: string }) => request({ url: 'likeElven', data, method: 'post' })
export * from './types'
