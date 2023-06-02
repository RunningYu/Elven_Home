<script setup lang='ts'>
// import { useRoute, onBeforeRouteUpdate } from 'vue-router'
import LVTabs from '@components/LVTabs/index.vue'

const isHome = inject('flag')

const obj = [{
  title: '校园精灵',
  list: [{ tab: '在校', path: '/elven' }, { tab: '毕业', path: '/elven' }, { tab: '休学', path: '/elven' }],
}, {
  title: '校园社区',
  list: [{ tab: '动态', path: "" }, { tab: '聊天', path: 'chat' }],
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
  <LVTabs :tab-list="obj">
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

  // :deep(.el-input__wrapper) {
  //   height: 30px;

  //   :deep(.el-input__inner) {
  //     height: 30px !important;
  //   }
  // }

  // :deep(.el-select--small) {
  //   .el-select-tags-wrapper {
  //     height: 30px !important;
  //     position: relative;
  //     width: 80%;

  //     .el-tag {
  //       position: absolute;
  //       left: 3px;
  //       top: 50%;
  //       padding: 0 10px;
  //       max-width: 100%;
  //       transform: translate(0, -60%);
  //     }
  //   }
  // }
}
</style>
