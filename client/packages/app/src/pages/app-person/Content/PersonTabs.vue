<script setup lang='ts'>
// import { useRoute, onBeforeRouteUpdate } from 'vue-router'
import LVTabs from '@components/LVTabs/index.vue'

const isHome = inject('flag')

const obj = [{
  title: '个人中心',
  list: [{ tab: '精灵动态', path: '/elven' }, { tab: '个人动态', path: '/elven' },],
}]

interface ListItem {
  value: string
  label: string
}
const loading = ref(false)
const options = ref<ListItem[]>([{ value: '猫', label: '猫' }, { value: '狗', label: '狗' }, { value: '鼠人dadadad', label: '鼠人dadadadadaddad' }])
const value = ref<string[]>([])
function remoteMethod(query: string) {
  console.log(query)
}
</script>

<template>
  <LVTabs :tab-list="obj" :hasHeight="false">
    <template v-if="isHome" #default>
      <div mb-10px>
        <el-select v-model="value" multiple filterable remote reserve-keyword placeholder="Please enter a keyword"
          remote-show-suffix :remote-method="remoteMethod" :loading="loading">
          <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
      </div>
    </template>
  </LVTabs>
</template>

<style lang='scss' scoped>
.tabs {
  height: calc(100vh - 60px);
  margin-right: 5px;
  background-color: var(--c-bg-tabs);
}
</style>
