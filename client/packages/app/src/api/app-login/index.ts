import { request } from '../basic'
import type { IreqLogin } from './types'

// 登录
export const reqLogin = (data: IreqLogin) => request({ url: '/login', data, method: 'post' })

// 获取用户信息
export const reUserInfo = (data: { token: string | number }) => request({ url: '/getUserInfoByToken', data, method: 'post' })
export * from './types'
