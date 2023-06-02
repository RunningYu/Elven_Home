  <!-- Mr.Liu -->
<script setup lang='ts'>
import AppContent from './Content/index.vue'
import AppHeader from './Header/index.vue'
import { useRoute } from 'vue-router';
import { getParams } from '@elven/utils';
import { useLoginStore } from '@stores'

const loginStore = useLoginStore()
loginStore.initUserInfo()


const route = useRoute()
const isHome = ref(true)

watchEffect(() => {
  const url = getParams(route.path, 1)
  if (url === 'home')
    isHome.value = true
  else
    isHome.value = false
})

provide('flag', isHome)

</script>

<template>
  <div class="home-container">
    <AppHeader v-if="isHome" />
    <AppContent :style="{
      'margin-top': isHome ? '0px' : '30px'
    }" />
  </div>
</template>

<style lang='scss' scoped>
</style>
