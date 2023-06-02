import { defineStore } from 'pinia'
import { reqElvenTypes, reqPublishElvenInfo } from '@api/app-person'
import type { IhotItem } from '..'

interface IGroupItem {
  elvenTypes: string[]
}

export const useElvenTypes = defineStore('useElvenTypes', {
  state: (): IGroupItem => {
    return {
      elvenTypes: [],
    }
  },
  actions: {
    async getElvenTypes() {
      const res = await reqElvenTypes()
      this.elvenTypes = res.data.data
    },
    async toPublishElvenInfo<T extends Partial<IhotItem>>(data: T) {
      const res = await reqPublishElvenInfo(data)
      if ((res as any).code === 200)
        return true
      return false
    },
  },
})
