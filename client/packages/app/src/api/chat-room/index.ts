import { request } from '../basic'
import type { IreqGroupList } from './types'

// 获取热搜列表
export const reqGroupList = (params: IreqGroupList) => request({ url: '/getChatUserList', params, method: 'get' })

export const reqChatHistory = <T>(params: T) => request({ url: '/getMessageList', method: 'get', params })
