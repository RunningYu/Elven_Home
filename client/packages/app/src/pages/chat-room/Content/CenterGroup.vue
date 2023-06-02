<script setup lang='ts'>
import { useChatStore } from '@/stores'
import type { IGroupItem } from '@/stores'
interface ListItem {
  value: string
  label: string
}

const list = ref<ListItem[]>([])
const options = ref<ListItem[]>([])
const value = ref<string[]>([])
const loading = ref(false)


const chatStore = useChatStore()

const remoteMethod = (query: string) => {
  if (query) {
    loading.value = true
    setTimeout(() => {
      loading.value = false
      options.value = list.value.filter((item) => {
        return item.label.toLowerCase().includes(query.toLowerCase())
      })
    }, 200)
  } else {
    options.value = []
  }
}

function toChat(item: IGroupItem) {
  chatStore.toChat(item)
}

function init() {
  chatStore.getGroupList()
}
init()

</script>

<template>
  <div w-280px flex-col flex class="group" ml-10px>
    <div w-full h-105px flex flex-col style="border-bottom:1.5px solid var(--c-html-color)">
      <span w-full px-10px h-50px flex items-center>
        <img src="../../../assets/app-home/3.jpg" loading="lazy" class="account-avatar rounded-full" h-40px w-40px
          select-none style="clip-path: none;">
        <p flex-1 text-sm px-10px>陈振宇</p>
        <ul flex w-60px h-full text-18px items-center justify-center>
          <li mr-10px cursor-pointer>
            <div class="i-ri:chat-new-fill"></div>
          </li>
          <li cursor-pointer>
            <div class="i-ri:list-unordered"></div>
          </li>
        </ul>
      </span>
      <!-- 筛选框 -->
      <span style="background-color: var(--c-chat-bg);" flex-1 flex items-center justify-center py-10px>
        <el-select v-model="value" w-260px multiple filterable remote reserve-keyword placeholder="查找联系人或群"
          :remote-method="remoteMethod" :loading="loading">
          <template #prefix>
            <div class="i-ri:search-line" text-sm></div>
          </template>
          <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
      </span>
    </div>
    <div flex-1>
      <ul w-full h-full style="height: calc(100vh - 165px);overflow: auto;background-color: var( --c-chat-bg);">
        <!-- 联系人 -->
        <li v-for="item in chatStore.list" :key="item.content" @click="toChat(item)" h-65px px-5px
          style="border-bottom: 1px solid var(--c-chat-border);" flex pr-10px cursor-pointer>
          <span w-60px h-full flex items-center justify-center mr-5px>
            <img :src="item.headPicture" loading="lazy" class="account-avatar rounded-full" h-45px w-45px select-none
              style="clip-path: none;">
          </span>
          <div w-full h-full flex flex-col justify-around text-10px py-10px class="info">
            <div flex justify-between>
              <span style="color: var(--c-home-content-title)">{{ item.targetId }}</span>
              <span>{{ item.updateTime }}</span>
            </div>
            <div class="info">
              {{ item.content }}
            </div>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>
<style lang='scss' scoped>
.group {
  background-color: var(--c-bg-tabs);
}

.info {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>
