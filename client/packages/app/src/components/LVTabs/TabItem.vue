  <!-- Mr.Liu -->
<script setup lang='ts'>
import { useRouter } from 'vue-router'
const props = defineProps<{
  tabList: {
    tab: string,
    path: string
  }[]
  tabTitle: string
  tabStart: number
}>()
const active = defineModel<number>('tabActive')

const router = useRouter()

function toCheckItem(i: number,path: string) {
  active.value = i + props.tabStart
  router.push({
    path
  })
}
</script>

<template>
  <div mb-10px>
    <span pl-5px text-sm class="tab-title">{{ tabTitle }}</span>
    <ul mt-10px text-15px>
      <li v-for="(k, i) in tabList" :key="k.path"
        :style="{ backgroundColor: active === (i + tabStart) ? 'var(--c-tab-hover) ' : 'transparent' }" class="tab"
        style="height: 35px;line-height: 35px" mb-5px rounded pl-5px @click="toCheckItem(i, k.path)">
        {{ k.tab }}
      </li>
    </ul>
  </div>
</template>

<style lang='scss' scoped>
.tab-title {
  color: var(--c-tab-title);
}

.tab {
  &:hover {
    background-color: var(--c-tab-hover) !important;
    cursor: pointer;
  }
}
</style>
