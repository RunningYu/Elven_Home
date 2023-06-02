<script setup lang='ts'>
import CenterArticle from './CenterArticle.vue'
import CenterRank from './CenterRank.vue'

// do not use same name with ref
import { useHomeStore } from '@/stores/app-home';

const showData = ref(false)

const loginStore = useHomeStore()

async function init() {
  await loginStore.getElvenList()
  showData.value = true
}

init()

</script>

<template>
  <div mx-10px flex-1>
    <CenterArticle v-for="k in loginStore.elvenList" :key="k.elvesId" :showData="showData" :item="k" />
  </div>
  <!-- 排行榜 -->
  <CenterRank />
</template>

<style lang='scss' scoped>
</style>
