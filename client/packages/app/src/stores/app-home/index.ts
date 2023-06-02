import { defineStore } from 'pinia'
import { reqElvenList, reqHotList, reqToLike } from '@api/app-home'
import { useLoginStore } from '../'

const store = useLoginStore()

export interface IhotItem {
  alive: number
  collegeName: string
  createTime: string
  fans: number
  firstFindPlace: string
  appearances: string
  haveLike: number
  headPicture: string
  health: string
  kind: string
  likeNumber: number
  links: string[]
  more: string | null
  name: string
  personality: string
  place: string
  sex: string
  updateTime: string
}

interface TAppRank {
  token: string | null
  kind: string | null
  hotPage: number
  hotSize: number
  elvenPage: number
  elvenSize: number
  hotList: IhotItem[]
  elvenList: any[]
}

export const useHomeStore = defineStore('useHomeStore', {
  state: (): TAppRank => {
    return {
      hotPage: 1,
      hotSize: 10,
      token: store.getToken,
      elvenPage: 1,
      elvenSize: 10,
      kind: null,
      hotList: [],
      elvenList: [],
    }
  },
  actions: {
    async getHotList(flush = false) {
      const { token, hotSize } = this
      this.hotPage = flush ? 1 : this.hotPage
      const res = await reqHotList({
        page: this.hotPage,
        size: hotSize,
        token: token || -1,
      })
      if (flush) {
        this.hotList.length = 0
        this.hotList.push(...res.data.data)
      }
      else {
        this.hotList.push(...res.data.data)
      }
      this.hotPage++
    },
    async getElvenList() {
      const { token, elvenSize } = this
      const res = await reqElvenList({
        page: this.elvenPage,
        size: elvenSize,
        token: token || '-1',
        kind: this.kind || '-1',
      })
      this.elvenList.push(...res.data.data)
      this.elvenPage = this.elvenPage + 1
    },
    async toLike(elvesId: string): Promise<{ haveLike: number; likeNumber: number }> {
      const res = await reqToLike({
        elvesId,
      })
      return res.data.data
    },
    clear() {
      this.hotPage = 1
      this.hotSize = 10
      this.token = store.getToken
      this.elvenPage = 1
      this.elvenSize = 10
      this.kind = null
      this.hotList = []
      this.elvenList = []
    },
  },
})
