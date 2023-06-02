import { defineStore } from 'pinia'
import { reqGroupList } from '@api/chat-room'
import { useLoginStore } from '../app-login'

const loginStore = useLoginStore()

interface IhistoryItem {
  token: string
  targetToken: string
  headHeadPicture: string
  content: string
  userName: string
  targetName: string
}

export interface IGroupItem {
  content: string
  updateTime: string
  headPicture: string
  targetId: string
  [key: string]: string
}
interface IGroupList {
  list: IGroupItem[]
  page: number
  size: number
  targetToken: string | null
  historyList: IhistoryItem[]
  historyPage: number
  historySize: number
}

export const useChatStore = defineStore('useChatStore', {
  state: (): IGroupList => {
    return {
      list: [],
      page: 1,
      size: 10,
      targetToken: null,
      historyList: [],
      historyPage: 1,
      historySize: 10,
    }
  },
  actions: {
    async getGroupList() {
      const { size, page } = this
      console.log(this.page)

      const res = await reqGroupList({
        page,
        size,
        token: (loginStore.getToken as string),
      })
      this.list.push(...res.data.data)
      this.list[0] && (this.targetToken = this.list[0].targetId)
      this.page++
    },
    async toChat(target: IGroupItem) {
      // const res = await reqChatHistory({
      //   page: this.historyPage,
      //   sizze: this.historySize,
      //   targetId: target.targetId,
      //   token: loginStore.getToken,
      // })
      // console.log(res)

      // this.historyList.push(...res.data.data)
      this.historyPage++
      this.targetToken = target.targetId
    },
    clear() {
      this.page = 1
      this.targetToken = null
      this.historyList = []
      this.historyPage = 1
      this.historySize = 10
    },
  },
  getters: {
    getList: (state) => {
      return state.list
    },
  },
})
