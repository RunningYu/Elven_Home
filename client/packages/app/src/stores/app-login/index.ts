import { defineStore } from 'pinia'
import type { IreqLogin } from '@api/app-login'
import { reUserInfo, reqLogin } from '@api/app-login'
import { useChatStore, useHomeStore } from '..'

interface TAppLogin {
  token: string | null
  userInfo: string | null
}

export interface IUserInfo {
  headPicture: string
  studentId: string
  userId: number
  username: string
}

export const useLoginStore = defineStore('useLoginStore', {
  state: (): TAppLogin => {
    return {
      token: localStorage.getItem('token') || null,
      userInfo: localStorage.getItem('userInfo') || null,
    }
  },
  actions: {
    async toLogin(data: IreqLogin) {
      try {
        const res = await reqLogin(data)
        this.userInfo = res.data.data
        localStorage.setItem('token', res.data.data.token)
        localStorage.setItem('userInfo', JSON.stringify(res.data.data))
      }
      catch (error) {
        console.log('号码不正确')
        return false
      }
      return true
    },
    async initUserInfo() {
      const res = await reUserInfo({ token: this.token ?? -1 })
      this.userInfo = res.data.data
      if (res.data.data) {
        localStorage.setItem('token', res.data.data.token)
        localStorage.setItem('userInfo', JSON.stringify(res.data.data))
      }
    },
    toOut() {
      const chatStore = useChatStore()
      const homeStore = useHomeStore()
      localStorage.clear()
      this.userInfo = null
      this.token = null
      chatStore.clear()
      homeStore.clear()
    },
  },
  getters: {
    getToken: (state): null | string => {
      console.log(state)
      return state.token
    },
    getUserInfo(state): IUserInfo | null {
      return state.userInfo as unknown as (IUserInfo | null)
    },
  },
})
