<script setup lang='ts'>
import TabItem from './TabItem.vue'
import { ref } from 'vue'
interface IProps {
  title: string
  list: {
    tab: string,
    path: string
  }[]
}

const props = withDefaults(defineProps<{
  tabList: IProps[]
  hasHeight: boolean
}>(), {
  hasHeight: true
})

const tabActive = ref<number>(0)

function getStart(idx: number) {
  let start = 0
  for (let i = 0; i < idx; i++)
    start += props.tabList[i].list.length
  return start
}
</script>

<template>
  <div class="tabs" :style="{
    height: hasHeight ? 'calc(100vh - 60px)' : 'auto'
  }" sticky top-60px w-182px flex flex-col rounded px-10px pt-10px>
    <slot />
    <TabItem v-for="(tab, i) in tabList" :key="tab.title" v-model:tab-active="tabActive" :tab-title="tab.title"
      :tab-list="tab.list" :tab-start="i === 0 ? 0 : getStart(i)" />
  </div>
</template>

<style lang='scss' scoped>
.tabs {
  margin-right: 5px;
  min-height: 400px;
  background-color: var(--c-bg-tabs);
}
</style>
