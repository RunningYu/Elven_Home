  <!-- Mr.Liu -->
<script setup lang='ts'>
import { useHomeStore } from '@/stores/app-home';

const rankStore = useHomeStore()

function load() {
  rankStore.getHotList()
}

rankStore.getHotList()

const list = computed(() => rankStore.hotList)


function getNew() {
  rankStore.getHotList(true)
}

</script>

<template>
  <div class="show" sticky top-60px w-282px flex flex-col rounded>
    <!-- login -->
    <div relative mb-20px h-150px w-full>
      <el-image style="width: 100%; height: 100%" rounded src="/src/assets/app-home/ad.png" fit="fill" />
    </div>
    <div>
      <ul px-15px>
        <li mb-20px flex justify-between>
          <span>校园热搜</span>
          <div flex cursor-pointer items-center>
            <div mr-10px text-sm class="i-ri:loop-right-line" />
            <span text-10px @click="getNew"> 点击刷新</span>
          </div>
        </li>
        <div v-if="list.length === 0">
          <el-skeleton :rows="12" animated mb-15px />
        </div>
        <li v-else v-infinite-scroll="load" h-480px w-full overflow-auto pl-10px>
          <div v-for="(i, idx) in list" :key="idx" items-cnter h-40px flex cursor-pointer items-center>
            <span w-20px text-center :style="{ color: idx < 3 ? '#f26d5f' : '#ea8011' }">{{ idx + 1 }}</span>
            <span ml-15px text-sm>{{ i.name }}</span>
            <span style="color:var(--c-tab-title)" ml-10px text-10px>{{ i.likeNumber }}</span>
          </div>
        </li>
        <li mt-18px flex justify-center text-12px>
          <div flex items-center justify-center rounded py-5px text-center
            style="width: 80%; ;background-color: var(--c-down-btn); cursor: pointer;" @click="load">
            <span>点击显示更多</span>
            <div class="i-ri:arrow-down-double-fill" ml-7px />
          </div>
        </li>
      </ul>
    </div>
    <!-- 广告 -->
    <div absolute bottom--120px left-0 h-110px w-full>
      <el-carousel indicator-position="none" arrow="nerver" height="110px" width="282px">
        <el-carousel-item v-for="item in 4" :key="item">
          <el-image style="width: 100%; height: 100%" rounded src="/src/assets/app-home/ad2.png" fit="fill" />
        </el-carousel-item>
      </el-carousel>
    </div>
  </div>
</template>

<style lang='scss' scoped>
.show {
  height: calc(100vh - 190px);
  background-color: var(--c-bg-tabs);
}
</style>
