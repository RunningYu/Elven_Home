import type { AxiosResponse, InternalAxiosRequestConfig } from 'axios'
import axios from 'axios'
import { useLoginStore } from '@stores'

export const request = axios.create({
  baseURL: import.meta.env.Elven_RequestBaseUrl || 'http://localhost:5173',
  withCredentials: false, // 跨域请求时是否需要使用凭证
  timeout: 30000,
})

// 添加请求拦截器
request.interceptors.request.use((config: InternalAxiosRequestConfig) => {
  const store = useLoginStore()
  const token: null | string = store.getToken

  if (token)
    config.headers!.Authorization = `Bearer ${token}`

  return config
}, (error: any) => {
  // 错误抛到业务代码
  error.data = {
    message: '服务器异常，请联系管理员！',
  }

  return Promise.reject(error)
})

// 添加响应拦截器
request.interceptors.response.use((response: AxiosResponse) => {
  /* 处理 http 错误，抛到业务代码 */
  const status = response.data.code
  const decide = status < 200 || status >= 300
  if (decide) {
    const message = showStatus(status)
    // console.log("处理 http 错误", message);
    if (typeof response.data === 'string')
      response.data = { message }
    else
      response.data.message = message

    return Promise.reject(response.data)
  }
  return response
}, (error: any) => {
  // console.log('请求错误', error, axios.isCancel(error), error.message);
  // if (axios.isCancel(error)) {
  //   // console.log('重复请求: ' + error.message);
  // }
  // else {
  //   const message = '请求超时或服务器异常，请检查网络或联系管理员！'
  // }

  return Promise.reject(error)
})

function showStatus(status: number) {
  let message = ''
  switch (status) {
    case 400:
      message = '请求错误(400)'
      break
    case 401:
      message = '未授权，请重新登录(401)'
      break
    case 403:
      message = '拒绝访问(403)'
      break
    case 404:
      message = '请求出错(404)'
      break
    case 408:
      message = '请求超时(408)'
      break
    case 500:
      message = '服务器错误(500)'
      break
    case 501:
      message = '服务未实现(501)'
      break
    case 502:
      message = '网络错误(502)'
      break
    case 503:
      message = '服务不可用(503)'
      break
    case 504:
      message = '网络超时(504)'
      break
    case 505:
      message = 'HTTP版本不受支持(505)'
      break
    default:
      message = `连接出错(${status})!`
  }
  return message
  // return `${message}，请检查网络或联系管理员！`
}
